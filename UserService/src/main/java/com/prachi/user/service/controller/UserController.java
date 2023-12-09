package com.prachi.user.service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prachi.user.service.entities.User;
import com.prachi.user.service.service.UserService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;

@RestController
@RequestMapping("/users")
public class UserController {
	@Autowired
	UserService userService;
	int retry=1;
	@GetMapping("/{id}")//this url calls rating and hotel services
	//@CircuitBreaker(name = "rating_hotel_breaker", fallbackMethod = "ratingHotelFallBack")//fallback method is called when there is open circuit//commenting this retry as we assume that service is slow
	//@Retry(name = "rating_hotel_retry", fallbackMethod = "ratingHotelFallBack")
	@RateLimiter(name = "rating_hotel_rateLimiter", fallbackMethod = "ratingHotelFallBack")
	public ResponseEntity<User> getUserById(@PathVariable String id){
		retry++;
		System.out.println(retry);
		User user= userService.getUserById(id);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	public ResponseEntity<User> ratingHotelFallBack(String id, Exception ex){
		ex.printStackTrace();
		User user= User.builder().name("dummy").about("i am a dummy").email("dumm@dummy.com").ratings(null).build();
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	@GetMapping("/")
	public ResponseEntity<List<User>> getUsers(){
		List<User> list= userService.getAllUsers();
		return new ResponseEntity<List<User>>(list, HttpStatus.OK);
	}
	
	@PostMapping("/")
	public ResponseEntity<User> saveUser(@RequestBody User user){
		User user2= userService.saveUser(user);
		return new ResponseEntity<User>(user2, HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable String id){
		User user2= userService.updateUser(id, user);
		return new ResponseEntity<User>(user2, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<User> deleteUser(@PathVariable String id){
		User user= userService.deleteUser(id);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
}
