package com.prachi.hotel.services;

import java.util.List;

import com.prachi.hotel.entities.Hotel;

public interface HotelService {
	Hotel createHotel(Hotel hotel);
	List<Hotel> getAllHotel();
	Hotel getHotelById(String id);
	Hotel updateHotel(String id, Hotel hotel);
	Hotel deleteHotel(String id);
}
