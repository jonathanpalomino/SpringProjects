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

public class CustomUserDetailsService implements UserDetailsService{

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		System.out.println("loadUserByUsername");
		UserDetails springUser = null;
		if(username.equals("admin")){
			springUser =  new User(
				     "admin",
				     "admin",
				     true,
				     true,
				     true,
				     true,
				     getAuthorities(2) );
		}
		else{
			System.out.println("UsernameNotFoundException");
			throw new UsernameNotFoundException("User " + username + " has no authorities");
		}
		
		return springUser;
	}

	private Collection<? extends GrantedAuthority> getAuthorities(int i) {
		List<GrantedAuthority> authList = (List<GrantedAuthority>) new ArrayList<GrantedAuthority>(2);
		   // All users are granted with ROLE_USER access
		   // Therefore this springUser gets a ROLE_USER by default
		SimpleGrantedAuthority userAuthority = new SimpleGrantedAuthority("ROLE_ADMIN");
		   System.out.println("Grant ROLE_USER to this user");
		   authList.add(userAuthority);
		   return authList;
	}

}
