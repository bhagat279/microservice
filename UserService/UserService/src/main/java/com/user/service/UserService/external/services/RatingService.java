package com.user.service.UserService.external.services;

import com.user.service.UserService.entities.Rating;
import org.apache.catalina.loader.ResourceEntry;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;

@FeignClient(name="RATING-SERVICE")
public interface RatingService {

    //get method

    @GetMapping("rating/getAllRatingByUserId/{userID}")
    List<Rating> getAllRatingsByUserId(@PathVariable String userID);



    //post method
    @PostMapping("rating/postRating")
    ResponseEntity<Rating> createRating(Rating value);


    //update method
    @PutMapping("rating/{id}")
    ResponseEntity<Rating> updateRating(@PathVariable  String id, Rating rating);


    //delete method
}
