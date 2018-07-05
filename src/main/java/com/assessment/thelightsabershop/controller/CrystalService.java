package com.assessment.thelightsabershop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.assessment.thelightsabershop.domain.Crystal;

@Controller
@Transactional
public class CrystalService {
	
	@Autowired
	private CrystalRepository crystalRepository;

	public Iterable<Crystal> getAllCrystals() {
		return crystalRepository.findAll();
	}
	
	public Crystal getCrystalById(long crystalId) {
		return crystalRepository.findById(crystalId).get();
	}
	
	public Crystal addCrystal(Crystal crystal) {
		Crystal result = crystalRepository.save(crystal);
		return result;
	}
	
	public Crystal getCrystalByNameAndColor(String name, String color){
		return crystalRepository.findByNameAndColorAllIgnoreCase(name, color);
	}
}
