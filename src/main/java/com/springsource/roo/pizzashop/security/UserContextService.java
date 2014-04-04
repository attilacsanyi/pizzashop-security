package com.springsource.roo.pizzashop.security;

import java.util.List;

public interface UserContextService {
	
	public String getUserName();
	public List<String> getUserRoles();
	
}
