package com.prachi.user.service.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.prachi.user.service.entities.Hotel;

@FeignClient(name= "HOTEL-SERVICE")//we need a feign client intercepter, so that the request made through feign client are stopped and adds token in header and then pass it forward
public interface HotelService {
	
	@GetMapping("/hotels/{hotelId}")
	Hotel getHotel(@PathVariable String hotelId);
}
