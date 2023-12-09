package com.prachi.hotel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prachi.hotel.entities.Hotel;
import com.prachi.hotel.services.HotelService;

@RestController
@RequestMapping("/hotels")
public class HotelController {

	@Autowired
	HotelService hotelService;
	
	@PreAuthorize("hasAuthority('SCOPE_internal') || hasAuthority('Admin')")
	@GetMapping("/")
	public ResponseEntity<List<Hotel>> getAllHotels() {
		return new ResponseEntity<>(hotelService.getAllHotel(),HttpStatus.OK);
	}
	//i don't want anyone to call this api directly except for those who have authority as internal
	@PreAuthorize("hasAuthority('SCOPE_internal')")
	@GetMapping("/{id}")
	public ResponseEntity<Hotel> getHotelById(@PathVariable String id) {
		return new ResponseEntity<>(hotelService.getHotelById(id),HttpStatus.OK);
	}
	
	@PreAuthorize("hasAuthority('Admin')")
	@PostMapping("/")
	public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel) {
		return new ResponseEntity<>(hotelService.createHotel(hotel),HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Hotel> updateHotel(@RequestBody Hotel hotel, @PathVariable String id) {
		return new ResponseEntity<>(hotelService.updateHotel(id, hotel),HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Hotel> deleteHotel(@PathVariable String id) {
	return new ResponseEntity<>(hotelService.deleteHotel(id),HttpStatus.OK);
	}
}
