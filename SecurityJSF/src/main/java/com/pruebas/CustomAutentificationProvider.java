package com.pruebas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class CustomAutentificationProvider implements AuthenticationProvider{

	//Este parametro ya fue inyectado en el aplicationContext, por eso no es necesario declarar
	@Autowired
	public CustomUserDetailsService serviceLogin;
	
	@Override
	public Authentication authenticate(Authentication authentication)
			throws AuthenticationException {
		//Captura los parametros usuario y password
		String usuario =authentication.getPrincipal().toString();
		String password =authentication.getCredentials().toString();
		System.out.println("user "+usuario);
		System.out.println("password "+password);
		Authentication provider;
		//Pregunta al servicio de usuario que pregunte si existe
		UserDetails detalle =getServiceLogin().loadUserByUsername(usuario);
		//Si existe retorna los detalles de dicho usuario, ejemplo (password)
		System.out.println("detalle "+detalle.getPassword());
		//Se compara el password retornado con el password ingresado
		if(detalle.getPassword().equals(password)){
			//Se instancia un token de autentificacion
			provider = new UsernamePasswordAuthenticationToken(detalle, password,detalle.getAuthorities());
		}
		else{
			//Se genera la excepcion de mala crdencial
			throw new BadCredentialsException("Credenciales invalidas");
		}
		//Retorno el proveedor de autentificacion
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
