package com.pruebas;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;

@Controller
@Scope("session")
public class LoginController implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String usuario;

	private String contrasena;
	
	private String ambiente;
	
	private String nombreUsuario;

	//Ya fue inyectado previamente, retorno su instancia
	@Autowired
	CustomAutentificationProvider customAutentificationProvider;

	public String validarUsuario() {
		String retorno = "";
		try {
			Authentication authenticate = customAutentificationProvider
					.authenticate(new UsernamePasswordAuthenticationToken(usuario, contrasena));

			if (authenticate.isAuthenticated()) {
				retorno = "valido";
				SecurityContextHolder.getContext().setAuthentication(authenticate);
			}
		} catch (BadCredentialsException e) {
		      FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de Credenciales", null);
		      FacesContext.getCurrentInstance().addMessage(null, facesMsg);
			retorno = "invalido";
		}
		catch (UsernameNotFoundException e) {
			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de Usuario no Existente", null);
		      FacesContext.getCurrentInstance().addMessage(null, facesMsg);
			retorno = "invalido";
		}
		return retorno;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public String getAmbiente() {
		return ambiente;
	}

	public void setAmbiente(String ambiente) {
		this.ambiente = ambiente;
	}

	/**
	 * @return the nombreUsuario
	 */
	public String getNombreUsuario() {
		return nombreUsuario;
	}

	/**
	 * @param nombreUsuario the nombreUsuario to set
	 */
	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}
}
