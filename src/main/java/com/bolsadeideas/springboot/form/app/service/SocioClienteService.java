package com.bolsadeideas.springboot.form.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.bolsadeideas.springboot.form.app.dao.ISocioClienteDao;
import com.bolsadeideas.springboot.form.app.entity.Socio;
import com.bolsadeideas.springboot.form.app.entity.SocioCliente;

@Service
public class SocioClienteService {
	
	@Autowired
	private ISocioClienteDao socioClienteDao;
	

	@Transactional
	public void save(SocioCliente socioCliente) {
		socioClienteDao.save(socioCliente);
	}

	public List<Socio> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	public SocioCliente findOne(Long id) {
		// TODO Auto-generated method stub
		return null;
	}


	public void delete(Long id) {
		// TODO Auto-generated method stub
		
	}


}
