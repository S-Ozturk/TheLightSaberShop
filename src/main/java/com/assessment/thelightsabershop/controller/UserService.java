package com.assessment.thelightsabershop.controller;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.assessment.thelightsabershop.config.SimpleSecurityController;
import com.assessment.thelightsabershop.domain.User;



@Controller
@Transactional
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired SimpleSecurityController simpleSecurityController;

	public void addNewUser (User user) {
		//Adding registration date for the long term because users age will be different next year 
		Calendar now = Calendar.getInstance();
		int year = now.get(Calendar.YEAR);
		user.setRegistrationYear(year);
		User result = userRepository.save(user);
		//Adding user to simpleSecurityController to let the user to be able to use his/her account
		simpleSecurityController.add(result.getEmail(), user.getPassword());
	}

	public Iterable<User> getAllUsers() {
		return userRepository.findAll();
	}
	
	//Init user is for adding all the users on the database to simpleSecurityController when application restarts
	public void initUsers() {
		Iterable<User> users = userRepository.findAll();
		for(User u: users) {
			System.out.println(u.getAge()+"-"+u.getEmail()+"-"+u.getForce()+"-"+u.getName()+"-"+u.getPassword()+"-"+u.getRole()+"-"+u.getId()+"-"+u.isDissolvedInForce());
			simpleSecurityController.add(u.getEmail(), u.getPassword());
		}
	}
	
	public User getUserByEmail(String email){
		return userRepository.findByEmail(email);
	}
}
