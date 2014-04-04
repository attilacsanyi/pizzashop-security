package com.springsource.roo.pizzashop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostFilter;

import com.springsource.roo.pizzashop.domain.Topping;
import com.springsource.roo.pizzashop.security.UserContextService;


public class ToppingServiceImpl implements ToppingService {
	
	@Autowired
	private UserContextService userContext;
	
	/**
	 * Before saving topping add created by info postfix to each Topping name
	 * @param topping the topping
	 */
    public void saveTopping(Topping topping) {
    	
    	topping.setName(topping.getName() + addCreatedBy());
    	
        topping.persist();
    }
	
	@PostFilter("hasPermission(filterObject, 'list')")
    public List<Topping> findToppingEntries(int firstResult, int maxResults) {
        return Topping.findToppingEntries(firstResult, maxResults);
    }
	
	/**
     * Add created by info
     * @return the created by info
     */
	private String addCreatedBy() {
		return " (created by: " + userContext.getUserName() + ")";
	}
}
