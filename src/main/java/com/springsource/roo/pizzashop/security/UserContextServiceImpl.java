package com.springsource.roo.pizzashop.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserContextServiceImpl implements UserContextService {

	@Override
	public String getUserName() {
		
		String username = "anonymus";
		
		Authentication auth = getAuthentication();
		
		if (auth != null)
		{
			username = auth.getName();
		}
		
		return username;
	}

	@Override
	public List<String> getUserRoles() {
		
		List<String> roles = null;
		
		Authentication auth = getAuthentication();
		Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
		
		if (auth != null &&  authorities != null)
		{
			roles = new ArrayList<String>();
			for (GrantedAuthority grantedAuthority : authorities) {
				roles.add(grantedAuthority.getAuthority());
			}
		}
		
		return roles;
	}
	
	private Authentication getAuthentication()
	{
		return SecurityContextHolder.getContext().getAuthentication();
	}

}
