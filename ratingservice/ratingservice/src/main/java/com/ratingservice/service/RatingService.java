package com.ratingservice.service;

import com.ratingservice.entities.Rating;

import java.util.List;

public interface RatingService {

    //Create Rating
    Rating createRating(Rating rating);

    //getAllRating
    List<Rating> getAllRating();

    //get all Ratings by userId
    List<Rating> getAllRatingByUserId(String userId);

    //get all Ratings by hotelId
    List<Rating> getAllRatingByHotelId(String hotelId);

}
