package com.user.service.UserService.services;

import com.user.service.UserService.entities.Hotel;
import com.user.service.UserService.entities.Rating;
import com.user.service.UserService.entities.User;
import com.user.service.UserService.exception.ResourceNotFoundException;
import com.user.service.UserService.external.services.HotelService;
import com.user.service.UserService.external.services.RatingService;
import com.user.service.UserService.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HotelService hotelService;

    @Autowired
    private RatingService ratingService;

    private Logger logger=LoggerFactory.getLogger(UserServiceImpl.class);

    //CREATE USER==========================================================================================
    @Override
    public User saveUser(User user) {
        String uniquserID = UUID.randomUUID().toString();
        user.setUserId(uniquserID);
        //User save = userRepository.save(user);


         return userRepository.save(user);
    }



    //GET ALL USERS ============================================================================================
    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();

    }



    //GET SINGLE USER===========================================================================================
    @Override
    public User getUser(String userId) {
       // geting single user from database using user-repository

        //userRepository.findById(userId).orElseThrow(ResourceNotFoundException::new); here we cant pass userId
          User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("resource not found for given id: " + userId));

      // fetch Rating of the above user with the help of Rating-Service
     // http://localhost:8084/rating/getAllRatingByUserId/3799a622-2785-42f2-9220-1482059107c0

        //Rating[] ratingsOfUser = restTemplate.getForObject("http://localhost:8084/rating/getAllRatingByUserId/"+user.getUserId(), Rating[].class);


       //calling rating service using service-name using rest template
       /* Rating[] ratingsOfUser = restTemplate.getForObject("http://RATING-SERVICE/rating/getAllRatingByUserId/"+user.getUserId(), Rating[].class);
        logger.info("{} ",ratingsOfUser);

        List<Rating> ratingslist = Arrays.stream(ratingsOfUser).toList();*/


        //calling rating service using feign client
        List<Rating> ratingslist = ratingService.getAllRatingsByUserId(user.getUserId());

        List<Rating> listOfRating = ratingslist.stream().map(rating -> {
            //api call to Hotel service to get hotel
            //http://localhost:8083/hotel/getHotelById/509e09a1-29ea-4276-aeb8-3f48ef0192f2
            //Calling hotel service using host(localhost) and port(8083)

           // ResponseEntity<Hotel> singleHotel = restTemplate.getForEntity("http://localhost:8083/hotel/getHotelById/" + rating.getHotelId(), Hotel.class);

            //calling hotel service using service name
//            ResponseEntity<Hotel> singleHotel = restTemplate.getForEntity("http://HOTELSERVICE/hotel/getHotelById/" + rating.getHotelId(), Hotel.class);
//            Hotel hotel = singleHotel.getBody();
//            logger.info("response status code: {} ", singleHotel.getStatusCode());


            //calling hotel service using feign client
            Hotel hotel = hotelService.getHotel(rating.getHotelId());

            //set hotel to rating
            rating.setHotel(hotel);

            //retrun rating
            return rating;

        }).collect(Collectors.toList());

        user.setRatings(listOfRating);

        return user;
    }

    //============================================DELETE USER=====================================================
    @Override
    public User deleteUser(String  userId) {
        User user = userRepository.findById(userId).get();
        userRepository.deleteById(userId);

        return user;
    }


    //======================================UPDATE USER============================================================
    @Override
    public User updateUser(String userId) {
        User user = userRepository.findById(userId).get();
//        User upadtedUser = user.builder().name("Ravi Bhagat singh").about("python developer").userId(userId).build();
//         User save = userRepository.save(upadtedUser);
        user.setName("ravi kumar singh");
        user.setAbout("pro java developer");
        User save = userRepository.save(user);

        return save;
    }


}
