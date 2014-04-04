package com.springsource.roo.pizzashop.service;

import org.springframework.security.access.prepost.PreAuthorize;

import com.springsource.roo.pizzashop.domain.PizzaOrder;


public class PizzaOrderServiceImpl implements PizzaOrderService {
	
	@PreAuthorize("!(authentication.name == 'tim'" +
					"and #pizzaOrder.pizzas.size() > 2)")
    public void savePizzaOrder(PizzaOrder pizzaOrder) {
        pizzaOrder.persist();
    }
	
}
