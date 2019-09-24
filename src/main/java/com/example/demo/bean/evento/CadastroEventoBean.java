package com.example.demo.bean.evento;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
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
	
	
	public void salvarEvento(Reserva reserva) {
		try {	
			reserva = service.salvar(reserva);
			FacesUtil.addInfoMessage("Reserva Atualizada com sucesso!!!");
		} catch (Exception e) {
			FacesUtil.addFatalMessage(e.getMessage());
		}
	}
	
	public StatusReserva[] getStatusReserva() {
		return StatusReserva.values();
	}

}
