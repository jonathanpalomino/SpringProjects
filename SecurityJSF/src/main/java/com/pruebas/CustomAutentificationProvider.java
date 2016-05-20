package com.pruebas;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.pruebas.CustomUserDetailsService;

@Component
public class CustomAutentificationProvider implements AuthenticationProvider{

	public CustomUserDetailsService serviceLogin;
	
	@Override
	public Authentication authenticate(Authentication authentication)
			throws AuthenticationException {
		String usuario =authentication.getPrincipal().toString();
		String password =authentication.getCredentials().toString();
		System.out.println("user "+usuario);
		System.out.println("password "+password);
		Authentication provider;
		UserDetails detalle =getServiceLogin().loadUserByUsername(usuario);
		
		System.out.println("detalle "+detalle.getPassword());
		if(detalle.getPassword().equals(password)){
			provider = new UsernamePasswordAuthenticationToken(detalle, password,detalle.getAuthorities());
		}
		else{
			throw new BadCredentialsException("Credenciales invalidas");
		}
		
		
		return provider;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return true;
	}

	public CustomUserDetailsService getServiceLogin() {
		return serviceLogin;
	}

	public void setServiceLogin(CustomUserDetailsService serviceLogin) {
		this.serviceLogin = serviceLogin;
	}

}
