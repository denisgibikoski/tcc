package com.example.demo.service.impl;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Reserva;
import com.example.demo.repository.ReservaRepository;
import com.example.demo.service.ReservaService;

@Service
public class ReservaServiceimpl implements ReservaService {
	
	@Autowired
	private ReservaRepository reservaRepository;


	@Override
	public List<Reserva> todos() {
		return reservaRepository.findAll();
	}

	@Override
	public void remover(Reserva reserva) {
		reservaRepository.delete(reserva);
	}

	@Override
	public Reserva salvar(Reserva reserva) {
		return reservaRepository.save(reserva);
	}

}
