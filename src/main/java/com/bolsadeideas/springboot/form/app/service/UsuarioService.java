package com.bolsadeideas.springboot.form.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bolsadeideas.springboot.form.app.dao.IUsuarioDao;
import com.bolsadeideas.springboot.form.app.entity.Usuario;

@Service
public class UsuarioService {
	
	@Autowired
	private IUsuarioDao usuarioDao;
	
	public List<Usuario> findAll() {
		// TODO Auto-generated method stub
		return usuarioDao.findAll();
	}

	@Transactional
	public void save(Usuario usuario) {
		usuarioDao.save(usuario);
		
	}

	public Usuario findById(Long id) {
		return usuarioDao.findById(id).orElse(null);
	}
	
	public void delete(Long id) {
		// TODO Auto-generated method stub
		
	}
	
	public Optional<Usuario> findByEmail(String email) {
		// TODO Auto-generated method stub
		return usuarioDao.findByEmail(email);
	}

}
