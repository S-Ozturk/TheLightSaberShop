package com.assessment.thelightsabershop.controller;

import org.springframework.data.repository.CrudRepository;

import com.assessment.thelightsabershop.domain.CombatForm;

public interface CombatFormRepository extends CrudRepository<CombatForm, Long> {
	
	CombatForm findByName(String name);

}
