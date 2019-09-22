package com.example.demo.service;

import com.example.demo.model.Usuario;

public interface UsuarioLogado {

	/**
	 * Retorna o usuário que se encontra na sessão.
	 * @return {@link Usuario}
	 */
	Usuario getUsuario();
}
