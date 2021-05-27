package com.unla.Grupo23OO22021.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class Encriptar {
	private BCryptPasswordEncoder passwordEncoder;
	
	protected Encriptar() {
		passwordEncoder=new BCryptPasswordEncoder();
	}

	public String encode(String string) {
		return passwordEncoder.encode(string);
	}
}
