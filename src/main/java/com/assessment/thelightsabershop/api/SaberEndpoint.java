package com.assessment.thelightsabershop.api;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.assessment.thelightsabershop.controller.CombatFormService;
import com.assessment.thelightsabershop.controller.CrystalService;
import com.assessment.thelightsabershop.controller.SaberService;
import com.assessment.thelightsabershop.domain.CombatForm;
import com.assessment.thelightsabershop.domain.Crystal;
import com.assessment.thelightsabershop.domain.Saber;



@Path("saber")
@Component
public class SaberEndpoint {
	
	@Autowired
	private SaberService saberService;
	
	@Autowired
	private CrystalService crystalService;
	
	@Autowired
	private CombatFormService combatFormService;

	// For getting All the Sabers As JSON Data
	@Path("/json")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllSabers(){
		Iterable <Saber> sabers= saberService.getAllSabers();
		return Response.ok(sabers).build();
	}
	
	// For getting single the Saber As JSON Data
	@Path("/{saberId}/single")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getSingleSaber(@PathParam("saberId") long id){
		if(saberService.checkSaberById(id)) {
			Saber saber = saberService.getSaberById(id);
			return Response.ok(saber).build();
		} else {
			return Response.status(204).build();
		}
	}
	
	//Single Saber adding
	@Path("/json")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public Response addSaber(Saber saber){
		if(saber.getAvailable() < 0) {
			saber.setAvailable(0);
		}
		saberService.addSaber(saber);
		return Response.status(205).build();
		//return Response.accepted(saber.getName() + " successfully added").build();	
	}
	
	// For getting All the Sabers As XML Data
	@Path("/xml")
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public  Response getAllSabersXML(){
		return Response.ok(new GenericEntity<Iterable<Saber>>(saberService.getAllSabers()) {}).build();
	}
	
	/*
	 * This code can be used for getting JSON data and XML data from the same adress
	@GET
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public @ResponseBody Response getAllSabersXML(){
		return Response.ok(new GenericEntity<Iterable<Saber>>(saberService.getAllSabers()) {}).build();
	}*/
	
	@Path("/xml")
	@POST
	@Consumes(MediaType.APPLICATION_XML)
	public @ResponseStatus Response addSaberXML(List<Saber> sabers) {
		//Created a loop for adding all of the Sabers
		for(Saber s : sabers ) {
			Saber tempSaber = new Saber();
			tempSaber.setId(s.getId());
			tempSaber.setName(s.getName());
			tempSaber.setAvailable(s.getAvailable());
			//Adds default combat form if there is no combat form assigned
			if(s.getSaberCombatForms() == null) {
				List<CombatForm> combatForms = new ArrayList<CombatForm>();
				combatForms.add(combatFormService.getCombatFormByName("Shii-Cho"));
				tempSaber.setSaberCombatForms(combatForms);
			}else {
				tempSaber.setSaberCombatForms(s.getSaberCombatForms());
			}
			//First checking crystal's name and color
			if(s.getCrystal().getName() != null && s.getCrystal().getColor() != null) {
				Crystal temp = crystalService.getCrystalByNameAndColor(s.getCrystal().getName(), s.getCrystal().getColor());
				//if that crystal exists uses that crystal else creates a new crystal
				if(temp != null) {
					tempSaber.setCrystal(temp);
				} else {
					Crystal newCrystal = new Crystal();
					newCrystal.setName(s.getCrystal().getName());
					newCrystal.setColor(s.getCrystal().getColor());
					newCrystal.setPowerUsageMultiplier(s.getCrystal().getPowerUsageMultiplier());
					newCrystal.setStockPrice(s.getCrystal().getStockPrice());
					newCrystal.setPlanet(s.getCrystal().getPlanet());
					if(s.getCrystal().getPlanet() == null)newCrystal.setPlanet("Unknown"); 
					tempSaber.setCrystal(crystalService.addCrystal(newCrystal));
				}
			} else {
				continue;
			}
			//a last moment check before saving the Saber
			if(tempSaber.getCrystal().getId() != null && tempSaber.getAvailable() >= 0 && tempSaber.getName() != null && tempSaber.getName().trim().length() >= 0) {
				saberService.addSaber(tempSaber);
			}
		}
		return Response.status(205).build();
	}
	
	@Path("/xml/{Z}")
	@POST
	@Consumes(MediaType.APPLICATION_XML)
	public @ResponseStatus Response addSaberXMLWithAge(List<Saber> sabers, @PathParam("Z") int age) {
		//Z is not necessary and does nothing...
		Response response = addSaberXML(sabers);
		return response;
	}
}
