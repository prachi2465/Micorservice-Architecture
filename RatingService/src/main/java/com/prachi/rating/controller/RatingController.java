package com.prachi.rating.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prachi.rating.entities.Rating;
import com.prachi.rating.service.RatingService;

@RestController
@RequestMapping("/ratings")
public class RatingController {
	@Autowired
	RatingService ratingService;
	
	@PostMapping("/")
	public ResponseEntity<Rating> createRating(@RequestBody Rating rating){
		return new ResponseEntity<Rating>(ratingService.createRating(rating), HttpStatus.CREATED);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Rating> getRating(@PathVariable String id){
		return new ResponseEntity<Rating>(ratingService.getById(id), HttpStatus.OK);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<Rating>> getAllRatings(){
		return new ResponseEntity<List<Rating>>(ratingService.getAll(), HttpStatus.OK);
	}
	
	@GetMapping("/hotels/{hotelId}")
	public ResponseEntity<List<Rating>> getRatingByHotelId(@PathVariable String hotelId){
		return new ResponseEntity<List<Rating>>(ratingService.getByHotelId(hotelId), HttpStatus.OK);
	}
	
	@GetMapping("/users/{userId}")
	public ResponseEntity<List<Rating>> getRatingByUserId(@PathVariable String userId){
		return new ResponseEntity<List<Rating>>(ratingService.getByUserId(userId), HttpStatus.OK);
	}
}
