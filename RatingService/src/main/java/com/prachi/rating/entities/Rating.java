package com.prachi.rating.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Rating {
	@Id
	private String ratingId;
	private String userId;
	private String hotelId;
	private String feedback;
	private int rating;
}
