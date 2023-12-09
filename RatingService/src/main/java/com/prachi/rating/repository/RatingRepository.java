package com.prachi.rating.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prachi.rating.entities.Rating;

public interface RatingRepository extends JpaRepository<Rating, String>{
public List<Rating> findByUserId(String id);
public List<Rating> findByHotelId(String id);
}
