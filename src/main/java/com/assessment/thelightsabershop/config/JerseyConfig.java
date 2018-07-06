package com.assessment.thelightsabershop.config;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import com.assessment.thelightsabershop.api.CombatFormEndpoint;
import com.assessment.thelightsabershop.api.CrystalEndpoint;
import com.assessment.thelightsabershop.api.SaberEndpoint;
import com.assessment.thelightsabershop.api.UserEndpoint;

@Component
@ApplicationPath("/Jedisabershop")
public class JerseyConfig extends ResourceConfig {
	public JerseyConfig(){
		register(CrystalEndpoint.class);
		register(CombatFormEndpoint.class);
		register(SaberEndpoint.class);
		register(UserEndpoint.class);
	}
}
