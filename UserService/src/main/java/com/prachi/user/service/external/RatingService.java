package com.prachi.user.service.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.prachi.user.service.entities.Rating;

@FeignClient(name = "RATING-SERVICE")
public interface RatingService {
	
	@PostMapping("/ratings/")
	Rating createRating(@RequestBody Rating rating);
}
