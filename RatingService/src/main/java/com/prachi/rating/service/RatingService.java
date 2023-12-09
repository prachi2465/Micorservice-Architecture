package com.prachi.rating.service;

import java.util.List;

import com.prachi.rating.entities.Rating;

public interface RatingService {
	Rating createRating(Rating rating);

	Rating getById(String id);

	List<Rating> getAll();

	List<Rating> getByUserId(String id);

	List<Rating> getByHotelId(String id);
}
