package com.prachi.hotel.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prachi.hotel.entities.Hotel;

public interface HotelRepository extends JpaRepository<Hotel, String> {

}
