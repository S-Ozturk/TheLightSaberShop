package com.assessment.thelightsabershop.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.assessment.thelightsabershop.controller.CombatFormService;
import com.assessment.thelightsabershop.domain.CombatForm;



@Path("combatform")
@Component
public class CombatFormEndpoint {
	
	@Autowired
	private CombatFormService combatFormService;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAlCombatForms(){
		Iterable <CombatForm> combatForms= combatFormService.getAllCombatForms();
		return Response.ok(combatForms).build();
	}
	
}
