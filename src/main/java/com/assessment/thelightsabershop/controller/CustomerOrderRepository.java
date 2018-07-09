package com.assessment.thelightsabershop.controller;


import org.springframework.data.repository.CrudRepository;

import com.assessment.thelightsabershop.domain.CustomerOrder;

public interface CustomerOrderRepository extends CrudRepository<CustomerOrder, Long> {

	Iterable<CustomerOrder> findByUser(int userId);
	
}
