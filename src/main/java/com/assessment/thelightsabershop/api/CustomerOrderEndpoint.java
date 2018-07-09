package com.assessment.thelightsabershop.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.assessment.thelightsabershop.controller.CustomerOrderService;
import com.assessment.thelightsabershop.controller.SaberService;
import com.assessment.thelightsabershop.controller.UserService;
import com.assessment.thelightsabershop.domain.CustomerOrder;
import com.assessment.thelightsabershop.domain.ResponseMessage;
import com.assessment.thelightsabershop.domain.Saber;
import com.assessment.thelightsabershop.domain.User;



@Path("order")
@Component
public class CustomerOrderEndpoint {
	
	@Autowired
	private CustomerOrderService customerOrderService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private SaberService saberService;

	@Path("/saber")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllCustomerOrders(){
		Iterable <CustomerOrder> customerOrders = customerOrderService.getAllCustomerOrders();
		return Response.ok(customerOrders).build();
	}
	
	@Path("/saber")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public Response addCustomerOrder(CustomerOrder customerOrder){
		customerOrder.setUser(userService.getUserByEmail(customerOrder.getUser().getEmail()));
		customerOrderService.addCustomerOrder(customerOrder);
		return Response.accepted(customerOrder.getId() + " successfully added").build();	
	}
	
	@Path("/saber/{id}")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public  Response /*ResponseEntity*/ addCustomerOrderWithSaberIdVariable(@PathParam("id") int saberId, CustomerOrder customerOrder){
		if(saberService.checkSaberById(saberId)) {
			User user = userService.getUserByEmail(customerOrder.getUser().getEmail());
			customerOrder.setUser(user);
			Saber saber = saberService.getSaberById(saberId);
			customerOrder.setUser(user);
			customerOrder.setSaber(saber);
			customerOrderService.addCustomerOrder(customerOrder);
			return Response.accepted(createResponseMessage("order succesfull",customerOrder.getOrderAmount() + " x " + saber.getName())).status(201).build();	
		}else {
			return Response.status(204).build();
		}
		
	}
	
	private ResponseMessage createResponseMessage(String message, String saberName) {
		ResponseMessage rm = new ResponseMessage();
			rm.setMessage(message);
			rm.setSaberName(saberName);
		return rm;
	}
	
}
