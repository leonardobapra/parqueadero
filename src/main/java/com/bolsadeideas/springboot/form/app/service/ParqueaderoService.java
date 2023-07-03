package com.bolsadeideas.springboot.form.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bolsadeideas.springboot.form.app.dao.IParqueaderoDao;
import com.bolsadeideas.springboot.form.app.entity.Parqueadero;

@Service
public class ParqueaderoService {
	
	@Autowired
	private IParqueaderoDao parqueaderoDao;
//	@Override
	public List<Parqueadero> findAll() {
		return parqueaderoDao.findAll();
	}

	@Transactional
	public void save(Parqueadero parqueadero) {
		parqueaderoDao.save(parqueadero);
		
	}

	public Parqueadero findById(Long id) {
		// TODO Auto-generated method stub
		return parqueaderoDao.findById(id).orElse(null);
	}

	public void delete(Long id) {
		parqueaderoDao.deleteById(id);
		
	}
	
	public Parqueadero findByRegistro(String registro) {
		// TODO Auto-generated method stub
		return parqueaderoDao.findByRegistro(registro).orElse(null);
	}
	
	public void update(Parqueadero parqueadero) {
		parqueaderoDao.save(parqueadero);
    }
	

}
