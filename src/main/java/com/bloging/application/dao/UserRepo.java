package com.bloging.application.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bloging.application.entity.User;


public interface UserRepo extends JpaRepository<User, Integer> {

	Optional<User> findByEmail(String email);
}
