package com.evertonborges.cursomc.services;

import org.springframework.security.core.context.SecurityContextHolder;

import com.evertonborges.cursomc.security.UserSS;

public class UserService {
	
	public static UserSS authenticated() {
		try {
			return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		} catch (Exception ex) {
			return null;
		}
	}

}