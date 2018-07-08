package com.assessment.thelightsabershop.controller;

import org.springframework.data.repository.CrudRepository;

import com.assessment.thelightsabershop.domain.User;

public interface UserRepository extends CrudRepository<User, Long> {
	
	User findByEmail(String email);
}
