package com.assessment.thelightsabershop.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;

import com.assessment.thelightsabershop.controller.CrystalService;
import com.assessment.thelightsabershop.domain.Crystal;



@Path("crystal")
@Component
public class CrystalEndpoint {
	
	@Autowired
	private CrystalService crystalService;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllCrystals(){
		Iterable <Crystal> crystals= crystalService.getAllCrystals();
		return Response.ok(crystals).build();
	}
	
	@Path("/xml")
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public @ResponseBody Response getAllCrystalsXML(){
		return Response.ok(new GenericEntity<Iterable<Crystal>>(crystalService.getAllCrystals()) {}).build();
	}
	
	@Path("/add")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public Response addCrystal(Crystal crystal){
		crystalService.addCrystal(crystal);
		return Response.accepted(crystal.getName() + " successfully added").build();	
	}
	
}
