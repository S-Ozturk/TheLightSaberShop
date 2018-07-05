package com.assessment.thelightsabershop.controller;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ResponseBody;

import com.assessment.thelightsabershop.config.SimpleSecurityController;
import com.assessment.thelightsabershop.domain.User;



@Controller
@Transactional
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired SimpleSecurityController simpleSecurityController;

	public String addNewUser (User user) {
		Calendar now = Calendar.getInstance();
		int year = now.get(Calendar.YEAR);
		user.setRegistrationYear(year);
		User result = userRepository.save(user);
		simpleSecurityController.add(result.getEmail(), user.getPassword());
		return "Saved";
	}

	public Iterable<User> getAllUsers() {
		return userRepository.findAll();
	}
	
	public void initUsers() {
		Iterable<User> users = userRepository.findAll();
		for(User u: users) {
			System.out.println(u.getAge()+"-"+u.getEmail()+"-"+u.getForce()+"-"+u.getName()+"-"+u.getPassword()+"-"+u.getRole()+"-"+u.getId()+"-"+u.isDissolvedInForce());
			simpleSecurityController.add(u.getEmail(), u.getPassword());
		}
	}
}
