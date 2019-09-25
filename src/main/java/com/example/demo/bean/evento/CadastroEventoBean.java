package com.example.demo.bean.evento;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.model.Reserva;
import com.example.demo.model.enums.StatusReserva;
import com.example.demo.service.ReservaService;
import com.example.demo.util.FacesUtil;


@Named
@ViewScoped
public class CadastroEventoBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private ReservaService service;
	
	private Reserva reserva;
	
	@PostConstruct
	public void inicializar() {

		limpar();
		FacesContext fContext = FacesContext.getCurrentInstance();
		if (fContext != null) {
			HttpServletRequest request = (HttpServletRequest) fContext.getExternalContext().getRequest();
			if (request.getParameter("reserva") != null) {
				String url = request.getParameter("reserva");
				Long id = Long.valueOf(url);

				reserva = service.porId(id);
				
				request.removeAttribute("usuario");
			}
		}

	}
	
	public void novoEvento() {
		try {
		
			reserva = service.salvar(reserva);

			FacesUtil.addInfoMessage("Evento " + reserva.getDescricao() + "  salvo !!");
			FacesUtil.addInfoMessage("Unidade N°:  " + reserva.getUsuario().getMoradia().getUnidade() + "  salvo !!");
		} catch (Exception e) {
			FacesUtil.addErrorMessage("ERRO :" + e.getMessage() + " - " + e.getCause());
			FacesUtil.addFatalMessage(e.getMessage());
		}

	}
	
	
	private void limpar() {
		reserva = new Reserva();
		
	}


	public void salvarEvento(Reserva reserva) {
		try {	
			reserva = service.salvar(reserva);
			FacesUtil.addInfoMessage("Reserva Atualizada com sucesso!!!");
		} catch (Exception e) {
			FacesUtil.addFatalMessage(e.getMessage());
		}
	}
	public boolean isEditando() {
		return reserva != null;
	}
	
	public StatusReserva[] getStatusReserva() {
		return StatusReserva.values();
	}

	public Reserva getReserva() {
		return reserva;
	}

	public void setReserva(Reserva reserva) {
		this.reserva = reserva;
	}

}
