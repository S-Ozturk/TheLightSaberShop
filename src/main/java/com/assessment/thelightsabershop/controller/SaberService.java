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
	
	//this method is for checking that saber is existing before calling getSaberById method
	public boolean checkSaberById(long saberId) {
		return saberRepository.existsById(saberId);
	}
	
	public Saber addSaber(Saber saber) {
		if(checkSaberById(saber.getId())) saber.setId(null);
		Saber result = saberRepository.save(saber);
		return result;
	}
}
