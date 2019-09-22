package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.model.Usuario;
import com.example.demo.repository.UsuarioRepository;
import com.example.demo.security.model.Permissao;
import com.example.demo.service.UsuarioService;


@Service
public class UsuarioServiceImpl implements UsuarioService  {

	@Autowired
	private UsuarioRepository usuarioRepositorio;
	
	private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

	@Override
	public List<Usuario> todos() {
		return usuarioRepositorio.findAll();
	}

	@Override
	public Usuario salvar(Usuario usuario) {
		usuario.setPermissao(getPermissao(usuario));
		usuario.setPassword(encoder.encode(usuario.getPassword()));
		return usuarioRepositorio.save(usuario);
	}

	private Permissao getPermissao(Usuario usuario) {
		if (usuario.isSindico()) {
			return Permissao.SINDICO;
		}else {
			return Permissao.USER;
		}
	}

	@Override
	public Usuario porId(Long id) {
		return usuarioRepositorio.findById(id).orElse(null);
	}

	@Override
	public void delete(Usuario usuario) {
		usuarioRepositorio.delete(usuario);
	}

	@Override
	public Usuario porEmail(String email) {
		return usuarioRepositorio.findByEmail(email);
	}


}
