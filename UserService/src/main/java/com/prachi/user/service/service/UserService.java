package com.prachi.user.service.service;

import java.util.List;

import com.prachi.user.service.entities.User;

public interface UserService {
	
	User saveUser(User user);
	List<User> getAllUsers();
	User getUserById(String id);
	User updateUser(String id, User user);
	User deleteUser(String id);
}
