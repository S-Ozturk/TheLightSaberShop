package com.assessment.thelightsabershop.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
	
	@Path("/add")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public Response addUser(User user){
		userService.addNewUser(user);
		return Response.accepted(user.getName() + " successfully added").build();	
	}

}
