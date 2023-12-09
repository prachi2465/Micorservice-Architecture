package com.prachi.user.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.prachi.user.service.entities.Rating;
import com.prachi.user.service.external.RatingService;

@SpringBootTest
class UserServiceApplicationTests {
	
	@Autowired
	RatingService ratingService;
	
	@Test
	void contextLoads() {
	}
	
	@Test
	void createRating() {
		Rating rating= Rating.builder().rating(9).hotel(null).hotelId("").feedback("created using feign client").build();
		Rating rating2= ratingService.createRating(rating);
		
		System.out.println(rating2.toString());
	}
}
