package com.assessment.thelightsabershop.api;

import java.net.URI;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.assessment.thelightsabershop.controller.UserService;
import com.assessment.thelightsabershop.domain.User;



@Path("user")
@Component
public class UserEndpoint {
	
	@Autowired
	private UserService userService;

	@Path("/all")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllUsers(){
		Iterable <User> users= userService.getAllUsers();
		return Response.ok(users).build();
	}
	
	//add user by posted json data
	@Path("/add")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public Response addUser(User user){
		userService.addNewUser(user);
		return Response.accepted(user.getRole() + " " + user.getName()).build();	
	}
	
	//add user by posted form
	@Path("/addByForm")
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public Response addUserByFormInput(@RequestBody @FormParam(value = "name") String name, @FormParam(value = "age") Integer age, @FormParam(value = "email") String email, @FormParam(value = "password") String password){
		User user = new User();
		user.setName(name);
		user.setAge(age);
		user.setEmail(email);
		user.setPassword(password);
		userService.addNewUser(user);
		return Response.accepted(user.getRole() + " " + user.getName() + " successfully added").build();
	}
	
}
