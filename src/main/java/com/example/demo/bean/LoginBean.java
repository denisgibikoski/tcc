package com.example.demo.bean;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.model.Usuario;
import com.example.demo.service.UsuarioService;

@Named
@SessionScoped
public class LoginBean implements Serializable {


	private static final long serialVersionUID = 1L;

	@Autowired (required = false)
	private FacesContext facesContext;

	@Autowired 
	private HttpServletRequest request;

	@Autowired 
	private HttpServletResponse response;
	
	@Autowired 
	private UsuarioService usuarioService;

	private Usuario usuario;
	private String email;

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public void sair() throws IOException {
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		ec.redirect(ec.getRequestContextPath() + "/logout");
	}
		
	public void login() throws ServletException, IOException {

		usuario = usuarioService.porEmail(email);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/Login.xhtml");
		dispatcher.forward(request, response);

		facesContext.responseComplete();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}