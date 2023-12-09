package com.prachi.user.service.serviceImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.prachi.user.service.entities.Hotel;
import com.prachi.user.service.entities.Rating;
import com.prachi.user.service.entities.User;
import com.prachi.user.service.exception.ResourceNotFoundException;
import com.prachi.user.service.external.HotelService;
import com.prachi.user.service.repo.UserRepository;
import com.prachi.user.service.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private HotelService hotelService;
	
	@Override
	public User saveUser(User user) {
		String uuid= UUID.randomUUID().toString();
		user.setUserId(uuid);
		return userRepo.save(user);
	}

	@Override
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		return userRepo.findAll();
	}

	@Override
	public User getUserById(String id) {
		// TODO Auto-generated method stub
		User user= userRepo.findById(id).orElseThrow(()->new ResourceNotFoundException());
		//we want to populate rating also, so we'll use get rating by user id function in rating service
		
		Rating[] ratings=restTemplate.getForObject("http://RATING-SERVICE/ratings/users/"+user.getUserId(), Rating[].class);
		System.out.println(ratings);
		
		
		for(Rating rating: ratings) {
			//Hotel hotel= restTemplate.getForObject("http://HOTEL-SERVICE/hotels/"+rating.getHotelId(), Hotel.class);
			Hotel hotel= hotelService.getHotel(rating.getHotelId());
			rating.setHotel(hotel);
		}
		user.getRatings().addAll(Arrays.asList(ratings));
		return user;
	}

	@Override
	public User updateUser(String id, User user) {
		// TODO Auto-generated method stub
		User user2= userRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException());
		user2.setName(user.getName());
		user2.setEmail(user.getEmail());
		user2.setAbout(user.getAbout());
		return userRepo.save(user2);
	}

	@Override
	public User deleteUser(String id) {
		// TODO Auto-generated method stub
		User user= userRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException());
		userRepo.delete(user);
		return user;
	}

}
