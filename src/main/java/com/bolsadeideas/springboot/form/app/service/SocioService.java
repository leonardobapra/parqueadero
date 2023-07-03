package com.bolsadeideas.springboot.form.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bolsadeideas.springboot.form.app.dao.ISocioDao;
import com.bolsadeideas.springboot.form.app.entity.Socio;

@Service
public class SocioService {
	
	@Autowired
	private ISocioDao socioDao;
	

	@Transactional
	public void save(Socio socio) {
		socioDao.save(socio);
	}

	public List<Socio> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	public Socio findOne(Long id) {
		// TODO Auto-generated method stub
		return null;
	}


	public void delete(Long id) {
		// TODO Auto-generated method stub
		
	}


}
