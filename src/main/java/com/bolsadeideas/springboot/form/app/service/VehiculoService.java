package com.bolsadeideas.springboot.form.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bolsadeideas.springboot.form.app.dao.IVehiculoDao;
import com.bolsadeideas.springboot.form.app.entity.Vehiculo;

@Service
public class VehiculoService {
	
	@Autowired
	private IVehiculoDao vehiculoDao;
	
	public List<Vehiculo> findAll() {
		// TODO Auto-generated method stub
		return vehiculoDao.findAll();
	}

	@Transactional
	public void save(Vehiculo vehiculo) {
		vehiculoDao.save(vehiculo);
		
	}

	public Vehiculo findOne(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	public void delete(Long id) {
		vehiculoDao.deleteById(id);
		
	}
	
	public Optional<Vehiculo> findByPlaca(String placa) {
		// TODO Auto-generated method stub
		return vehiculoDao.findByPlaca(placa);
	}
	
	public void update(Vehiculo vehiculo) {
		vehiculoDao.save(vehiculo);
    }

}
