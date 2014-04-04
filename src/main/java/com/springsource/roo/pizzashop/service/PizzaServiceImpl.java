package com.springsource.roo.pizzashop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;

import com.springsource.roo.pizzashop.domain.Pizza;
import com.springsource.roo.pizzashop.security.UserContextService;


public class PizzaServiceImpl implements PizzaService {
	
	@Autowired
	private UserContextService userContext;
	
	//@Secured({"ROLE_USER", "ROLE_ADMIN"})
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public void savePizza(Pizza pizza) {
		
		pizza.setName(pizza.getName() + addCreatedBy());
        pizza.persist();
    }
	
	/**
     * Add created by info
     * @return the created by info
     */
	private String addCreatedBy() {
		return " (created by: " + userContext.getUserName() + ")";
	}
	
}
