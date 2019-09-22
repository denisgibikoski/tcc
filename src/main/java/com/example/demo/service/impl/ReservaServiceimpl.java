package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.model.Reserva;
import com.example.demo.repository.ReservaRepository;
import com.example.demo.service.ReservaService;

public class ReservaServiceimpl implements ReservaService {
	
	@Autowired
	private ReservaRepository reservaRepository;

	@Override
	public List<Reserva> getTodas() {
		return reservaRepository.findAll();
	}

}
