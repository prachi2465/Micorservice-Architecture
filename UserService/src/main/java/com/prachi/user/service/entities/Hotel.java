package com.prachi.user.service.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Hotel {
	private String hotelId;
	private String name;
	private String location;
	private String about;
}
