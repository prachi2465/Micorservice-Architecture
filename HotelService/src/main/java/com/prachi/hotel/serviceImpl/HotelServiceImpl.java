package com.prachi.hotel.serviceImpl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prachi.hotel.Exception.ResourceNotFoundException;
import com.prachi.hotel.entities.Hotel;
import com.prachi.hotel.repository.HotelRepository;
import com.prachi.hotel.services.HotelService;

@Service
public class HotelServiceImpl implements HotelService {
	
	@Autowired
	HotelRepository hotelRepository;
	
	@Override
	public Hotel createHotel(Hotel hotel) {
		String id= UUID.randomUUID().toString();
		hotel.setHotelId(id);
		return hotelRepository.save(hotel);
	}

	@Override
	public List<Hotel> getAllHotel() {
		return hotelRepository.findAll();
	}

	@Override
	public Hotel getHotelById(String id) {
		return hotelRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException());
	}

	@Override
	public Hotel updateHotel(String id, Hotel hotel) {
		Hotel hotel2= hotelRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException());
		hotel2.setName(hotel.getName());
		hotel2.setLocation(hotel.getLocation());
		hotel2.setAbout(hotel.getAbout());
		return hotelRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException());
	}

	@Override
	public Hotel deleteHotel(String id) {
		Hotel hotel= hotelRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException());
		hotelRepository.delete(hotel);
		return hotel;
	}

}
