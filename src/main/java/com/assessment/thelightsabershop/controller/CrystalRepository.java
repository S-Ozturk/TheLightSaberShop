package com.assessment.thelightsabershop.controller;


import org.springframework.data.repository.CrudRepository;

import com.assessment.thelightsabershop.domain.Crystal;

public interface CrystalRepository extends CrudRepository<Crystal, Long> {

	Crystal findByNameAndColorAllIgnoreCase(String name,String color);
}
