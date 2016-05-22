package com.pruebas;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService{

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		System.out.println("loadUserByUsername");
		//Pregunto si el usuario existe, en este caso esta hardCoded servic
		//pero debe ser reemplazado con un origen de datos como una tabla
		//un webService u otro
		UserDetails springUser = null;
		if(username.equals("admin")){
			//Genero un objeto usuario con el nombre de usuario password  y otros detalles
			springUser =  new User(
				     "admin",
				     "admin",
				     true,
				     true,
				     true,
				     true,
				     //Con este metodo retorno los Roles de este usuario
				     getAuthorities(2) );
		}
		else{
			//De no existir se lanza la excepcion de usuario no existente
			System.out.println("UsernameNotFoundException");
			throw new UsernameNotFoundException("User " + username + " has no authorities");
		}
		//Retorno el usuario
		return springUser;
	}

	private Collection<? extends GrantedAuthority> getAuthorities(int i) {
		List<GrantedAuthority> authList = (List<GrantedAuthority>) new ArrayList<GrantedAuthority>(2);
		   // Todos los usuarios nacen con acceso ROLE_USER
		   // En este caso le agrego el Rol Administrador como prueba
		SimpleGrantedAuthority userAuthority = new SimpleGrantedAuthority("ROLE_ADMIN");
		   System.out.println("Grant ROLE_USER to this user");
		   authList.add(userAuthority);
		   return authList;
	}

}
