package com.prachi.rating.serviceImpl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prachi.rating.entities.Rating;
import com.prachi.rating.exception.ResourceNotFoundException;
import com.prachi.rating.repository.RatingRepository;
import com.prachi.rating.service.RatingService;

@Service
public class RatingServiceImpl implements RatingService{
	@Autowired
	RatingRepository ratingRepository;
	
	@Override
	public Rating createRating(Rating rating) {
		String id= UUID.randomUUID().toString();
		rating.setRatingId(id);
		return ratingRepository.save(rating);
	}

	@Override
	public Rating getById(String id) {
		return ratingRepository.findById(id).orElseThrow(()->new ResourceNotFoundException());
	}

	@Override
	public List<Rating> getAll() {
		// TODO Auto-generated method stub
		return ratingRepository.findAll();
	}

	@Override
	public List<Rating> getByUserId(String id) {
		// TODO Auto-generated method stub
		return ratingRepository.findByUserId(id);
	}

	@Override
	public List<Rating> getByHotelId(String id) {
		// TODO Auto-generated method stub
		return ratingRepository.findByHotelId(id);
	}

	

}
