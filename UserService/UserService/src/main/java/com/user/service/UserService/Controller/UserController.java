package com.user.service.UserService.Controller;


import com.user.service.UserService.entities.User;
import com.user.service.UserService.services.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("user/")
public class UserController {

    @Autowired
    private UserService userService;

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    //===================================================postuser=======================================================
    @PostMapping("postuser")
    public ResponseEntity<User> createUser(@RequestBody User user)
    {
        User user1 = this.userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user1);
    }
    //===================================================getAllUser=====================================================
    @GetMapping("getAllUser")
    public ResponseEntity<List<User>> getAllUser()
    {
        List<User> allUser = this.userService.getAllUser();

        return  ResponseEntity.status(HttpStatus.CREATED).body(allUser);
    }


//===================================================getSingleUser======================================================

    int retryCount =1;

    @GetMapping("getSingleUser/{userId}")
   // @CircuitBreaker(name = "ratingHotelBreaker", fallbackMethod = "ratingHotelFallback")
    //@Retry(name ="ratingHotelService", fallbackMethod = "ratingHotelRetry")
    @RateLimiter(name = "userRateLimiter", fallbackMethod = "userRateLimiterFallback" )
    public ResponseEntity<User> getSingleUser(@PathVariable String userId)
    {
        logger.info("get single user handler : UserController");
        logger.info("Retry count: {}",retryCount);
        retryCount++;
        User singleUser = this.userService.getUser(userId);
        return  ResponseEntity.status(HttpStatus.OK).body(singleUser);
    }



    //Creating fallback method for CircuitBreaker
    // return type should same as method on which @CircuitBreaker applied.
    public ResponseEntity<User> ratingHotelRetry(String userId, Exception ex )
    {
      // logger.info("Fallback is executed because service is down :", ex.getMessage());


        User dummyUser = User.builder()
                             .name("Dummy-retry")
                             .email("dummy@gmail.com")
                             .about("This user is created as dummy, because some service is down and try to retrying")
                             .build();

        return new ResponseEntity<>(dummyUser,HttpStatus.OK);
    }

    //Creating fallback method for ----retry
    public ResponseEntity<User> ratingHotelFallback(String userId, Exception ex )
    {
        // logger.info("Fallback is executed because service is down :", ex.getMessage());


        User dummyUser = User.builder()
                .name("Dummy")
                .email("dummy@gmail.com")
                .about("This user is created as dummy, because some service is down")
                .build();

        return new ResponseEntity<>(dummyUser,HttpStatus.OK);
    }

    //Creating fallback method for ----rateLimiter
    public ResponseEntity<User> userRateLimiterFallback(String userId, Exception ex )
    {
         logger.info("Fallback is executed because of request limit cross per/sec :", ex.getMessage());


        User dummyUser = User.builder()
                .name("Dummy-ratelimiter")
                .email("dummy@gmail.com")
                .about("using Rate Limiter")
                .build();

        return new ResponseEntity<>(dummyUser,HttpStatus.OK);
    }

    //========================================deleteuser================================================================
    @DeleteMapping("/deleteuser/{userid}")
    public ResponseEntity<User> deleteUser(@PathVariable String userid)
    {
        User user1 = userService.deleteUser(userid);
        return  new ResponseEntity<>(user1,HttpStatus.OK);
    }


    //========================================updateuser================================================================
    @PutMapping("/updateuser/{userId}")
   public ResponseEntity<User> updateUser(@PathVariable String userId)
   {
       User user = userService.updateUser(userId);

       return new ResponseEntity<User>(user,HttpStatus.CREATED);
   }
}
