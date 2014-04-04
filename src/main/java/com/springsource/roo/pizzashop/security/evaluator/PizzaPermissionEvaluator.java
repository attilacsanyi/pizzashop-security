package com.springsource.roo.pizzashop.security.evaluator;


import java.io.Serializable;

import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;

import com.springsource.roo.pizzashop.domain.Base;
import com.springsource.roo.pizzashop.domain.Topping;
import com.springsource.roo.pizzashop.security.DeletableEntity;

public class PizzaPermissionEvaluator implements PermissionEvaluator {

	public boolean hasPermission(Authentication auth, Object target, Object perm) {
		
		String userName = auth.getName();
		
		/** *****************************/
		/** TOPPING related permissions */
		/** *****************************/
		if (target instanceof Topping) {
			
			Topping topping = (Topping)target;
			
			if (perm.equals("list"))
			{
					/** 7. The guest can see only his/her toppings */
					if (userName.equals("guest"))
					{
						return (topping.getName().contains("created by: guest")) ? true : false;
					}
					
					/** 10. Only the own toppings are deletable */
					if (!topping.getName().contains("created by: " + userName))
					{
						topping.setDeletable(false);
					}
			}

			return true;
		}
		/** **************************/
		/** BASE related permissions */
		/** **************************/
		else if (target instanceof Base)
		{		
			Base base = (Base)target;
			
			if (perm.equals("update"))
			{
				/** 9. Only the own Base can be update */
				return (base.getName().contains("created by: " + userName)) ? true : false;
			}
			
			return true;
		}
		
		throw new UnsupportedOperationException("hasPermission not supported for object <"+target+"> and permission <"+perm+">");
	}

	public boolean hasPermission(Authentication authentication,
			Serializable targetId, String targetType, Object permission) {
		throw new UnsupportedOperationException("Not supported");
	}
}
