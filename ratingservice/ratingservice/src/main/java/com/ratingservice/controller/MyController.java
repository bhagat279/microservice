package com.ratingservice.controller;

import com.ratingservice.entities.Rating;
import com.ratingservice.service.RatingService;
import org.apache.catalina.loader.ResourceEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("rating/")
public class MyController {

    @Autowired
    private RatingService ratingService;

    @PostMapping("postRating")
    public ResponseEntity<Rating> createRating(@RequestBody Rating rating)
    {
        Rating rating1 = this.ratingService.createRating(rating);

        return  ResponseEntity.status(HttpStatus.CREATED).body(rating1);

    }

    @GetMapping("getAllRating")
    public ResponseEntity<List<Rating>> getAllRating()
    {
       return ResponseEntity.ok(ratingService.getAllRating());
    }



    @GetMapping("getAllRatingByUserId/{userID}")
    public ResponseEntity<List<Rating>> getRatingByUserId(@PathVariable String userID)
    {
        return ResponseEntity.ok(ratingService.getAllRatingByUserId(userID));
    }



    @GetMapping("getAllRatingByHotelId/{hotelId}")
    public ResponseEntity<List<Rating>> getAllRatingByHotelId(@PathVariable String hotelId)
    {
        return ResponseEntity.ok(ratingService.getAllRatingByHotelId(hotelId));
    }
}
