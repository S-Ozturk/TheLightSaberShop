package com.assessment.thelightsabershop.controller;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.assessment.thelightsabershop.config.SimpleSecurityController;
import com.assessment.thelightsabershop.domain.CombatForm;



@Controller
@Transactional
public class CombatFormService {
	
	@Autowired
	private CombatFormRepository combatFormRepository;
	
	@Autowired 
	private SimpleSecurityController simpleSecurityController;

	public void addNewCombatForm (CombatForm combatForm) {
		combatFormRepository.save(combatForm);
	}

	public Iterable<CombatForm> getAllCombatForms() {
		return combatFormRepository.findAll();
	}
	
	public CombatForm getCombatFormByName(String name) {
		return combatFormRepository.findByName(name);
	}
	
	//Init combatForm is for adding all the combatForms on the database to simpleSecurityController when application restarts
	public void initCombatForms() {
		CombatForm combatFormShiiCho = getCombatFormByName("Shii-Cho");
		if(combatFormShiiCho == null) {
			String[] combatFormsArray = {"Shii-Cho", "Makashi", "Soresu", "Ataru", "Shien (Djem So)", "Niman", "Juyo (Vaapad)"};
			for(String s: combatFormsArray) {
				System.out.println(s);
				CombatForm combatForm = new CombatForm();
				combatForm.setName(s);
				addNewCombatForm(combatForm);
			}
		}
	}
	
}
