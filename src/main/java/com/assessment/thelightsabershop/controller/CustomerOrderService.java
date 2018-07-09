package com.assessment.thelightsabershop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.assessment.thelightsabershop.domain.CustomerOrder;

@Controller
@Transactional
public class CustomerOrderService {
	
	@Autowired
	private CustomerOrderRepository customerOrderRepository;
	
	@Autowired
	private SaberService saberService;

	public Iterable<CustomerOrder> getAllCustomerOrders() {
		return customerOrderRepository.findAll();
	}
	
	public CustomerOrder getCustomerOrderById(long customerOrderId) {
		return customerOrderRepository.findById(customerOrderId).get();
	}
	
	public CustomerOrder addCustomerOrder(CustomerOrder customerOrder) {
		CustomerOrder result = customerOrderRepository.save(customerOrder);
		if(result != null) {
			saberService.getSaberById(result.getSaber().getId()).setAvailable(result.getSaber().getAvailable() - result.getOrderAmount());
		}
		return result;
	}
	
}
