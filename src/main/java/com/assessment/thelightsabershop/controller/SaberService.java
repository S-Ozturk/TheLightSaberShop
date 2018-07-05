package com.assessment.thelightsabershop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.assessment.thelightsabershop.domain.Saber;

@Controller
@Transactional
public class SaberService {
	
	@Autowired
	private SaberRepository saberRepository;

	public Iterable<Saber> getAllSabers() {
		return saberRepository.findAll();
	}
	
	public Saber getSaberById(long saberId) {
		return saberRepository.findById(saberId).get();
	}
	
	public Saber addSaber(Saber saber) {
		Saber result = saberRepository.save(saber);
		return result;
	}
}
