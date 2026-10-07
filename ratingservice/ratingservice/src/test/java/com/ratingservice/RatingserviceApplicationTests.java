package com.ratingservice;

import com.ratingservice.entities.Rating;
import com.ratingservice.service.RatingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

@SpringBootTest
class RatingserviceApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	private RatingService ratingService;

	@Test
	void createRating()
	{
		Rating rating = Rating.builder().ratingId("5050").rating("9").hotelId("101010101").userId("5052054").feedback("bahut acha").build();
		Rating rating1 =ratingService.createRating(rating);
		System.out.println(rating1.getRatingId());
	}

}
