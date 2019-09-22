package com.example.demo.bean.usuario;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.model.UnidadeMoradia;
import com.example.demo.model.Usuario;
import com.example.demo.model.enums.StatusCadastro;
import com.example.demo.model.enums.TipoUsuario;
import com.example.demo.service.UsuarioService;
import com.example.demo.util.FacesUtil;

@Named
@ViewScoped
public class UsuariosBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired(required = false)
	private UsuarioService usuarioService;


	private Usuario usuario;
	
	private boolean sindico = false;
	private boolean ativar = false;
	private boolean morador = false;

	@PostConstruct
	public void inicializar() {

		limpar();
		FacesContext fContext = FacesContext.getCurrentInstance();
		if (fContext != null) {
			HttpServletRequest request = (HttpServletRequest) fContext.getExternalContext().getRequest();
			if (request.getParameter("usuario") != null) {
				String url = request.getParameter("usuario");
				Long id = Long.valueOf(url);

				usuario = usuarioService.porId(id);
				if (usuario.getMoradia()!= null) {
					morador = true;
					mostarCampos();
				}
				request.removeAttribute("usuario");
			}
		}

	}

	public void limpar() {
		sindico = false;
		ativar = false;
		morador = false;
		usuario = new Usuario();
		usuario.setMoradia(new UnidadeMoradia());
	}

	public boolean isEditando() {
		return usuario != null;
	}

	public void novoUsuario() {
		try {
			usuario.setStatusUsuario(StatusCadastro.PEDENTE);
			if (morador) {
				usuario.setTipoUsuario(TipoUsuario.MORADOR);
			}
			usuario.setSindico(sindico);
			usuario.getMoradia().setStatusUnidadeMoradia(StatusCadastro.PEDENTE);
			usuario.getMoradia().setUsuario(usuario);
			usuario = usuarioService.salvar(usuario);

			FacesUtil.addInfoMessage("Usuario " + usuario.getNome() + "  salvo !!");
			FacesUtil.addInfoMessage("Unidade N°:  " + usuario.getMoradia().getUnidade() + "  salvo !!");
		} catch (Exception e) {
			FacesUtil.addErrorMessage("ERRO :" + e.getMessage() + " - " + e.getCause());
			FacesUtil.addFatalMessage(e.getMessage());
		}

	}

	public void mostarCampos() {
		if (usuario.isSindico()) {
			sindico = true;
		}
		if (morador == true) {
			ativar = true;
		} else if (morador == false) {
			ativar = false;
		}

	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public boolean isSindico() {
		return sindico;
	}

	public void setSindico(boolean sindico) {
		this.sindico = sindico;
	}

	public boolean isMorador() {
		return morador;
	}

	public void setMorador(boolean morador) {
		this.morador = morador;
	}

	public boolean isAtivar() {
		return ativar;
	}

	public void setAtivar(boolean ativar) {
		this.ativar = ativar;
	}

}
