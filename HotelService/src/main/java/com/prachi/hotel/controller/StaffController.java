package com.prachi.hotel.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/staff")
public class StaffController {
@GetMapping("/")
public List<String> getList(){
	return new ArrayList<>(Arrays.asList("Prachi","Anurag"));
}
}
