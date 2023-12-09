package com.prachi.user.service.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prachi.user.service.entities.User;

public interface UserRepository extends JpaRepository<User, String> {

}
