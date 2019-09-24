package com.example.demo.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.model.Reserva;
import com.example.demo.model.enums.StatusReserva;
import com.example.demo.service.ReservaService;
import com.example.demo.util.FacesUtil;

@Named
@ViewScoped
public class HomeBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private ReservaService service;
	private Reserva reserva;
	private List<Reserva> listaReservas;
	private ScheduleModel reservas;
	private ScheduleEvent event;

	@PostConstruct
	public void inicializar() {
		reservas = new DefaultScheduleModel();
		reserva = new Reserva();
		try {
			listaReservas = service.todos();
		} catch (Exception e) {
			e.printStackTrace();
			FacesUtil.addFatalMessage("Erro FATAL " + e.getMessage());
		}
		for (Reserva reserva : listaReservas) {
			DefaultScheduleEvent evt = new DefaultScheduleEvent();
			evt.setEndDate(reserva.getDataFinal());
			evt.setStartDate(reserva.getDataInicial());
			evt.setDescription(reserva.getDescricao());
			evt.setTitle(reserva.getUsuario().getNome());
			reservas.addEvent(evt);
		}

	}

	public StatusReserva[] getstatusReservas() {
		return StatusReserva.values();
	}

	public ScheduleModel getReservas() {
		return reservas;
	}

	public void setReservas(ScheduleModel reservas) {
		this.reservas = reservas;
	}

	public Reserva getReserva() {
		return reserva;
	}

	public void setReserva(Reserva reserva) {
		this.reserva = reserva;
	}

	public List<Reserva> getListaReservas() {
		return listaReservas;
	}

	public void setListaReservas(List<Reserva> listaReservas) {
		this.listaReservas = listaReservas;
	}

	public ScheduleEvent getEvent() {
		return event;
	}

	public void setEvent(ScheduleEvent event) {
		this.event = event;
	}

}
