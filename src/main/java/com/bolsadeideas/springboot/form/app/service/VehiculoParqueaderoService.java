package com.bolsadeideas.springboot.form.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bolsadeideas.springboot.form.app.dao.IVehiculoDao;
import com.bolsadeideas.springboot.form.app.dao.IVehiculoParqueaderoDao;
import com.bolsadeideas.springboot.form.app.entity.Parqueadero;
import com.bolsadeideas.springboot.form.app.entity.Vehiculo;
import com.bolsadeideas.springboot.form.app.entity.VehiculoParqueadero;

@Service
public class VehiculoParqueaderoService {
	
	@Autowired
	private IVehiculoParqueaderoDao vehiculoParqueaderoDao;
	
	public List<VehiculoParqueadero> findAll() {
		// TODO Auto-generated method stub
		return vehiculoParqueaderoDao.findAll();
	}

	@Transactional
	public void save(VehiculoParqueadero vehiculoParqueadero) {
		vehiculoParqueaderoDao.save(vehiculoParqueadero);
		
	}

	public VehiculoParqueadero findOne(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	public void delete(Long id) {
		vehiculoParqueaderoDao.deleteById(id);
		
	}
	
//	public Optional<Vehiculo> findByPlaca(String placa) {
//		// TODO Auto-generated method stub
//		return vehiculoParqueaderoDao.findByPlaca(placa);
//	}
	
	public void update(VehiculoParqueadero vehiculoParqueadero) {
		vehiculoParqueaderoDao.save(vehiculoParqueadero);
    }
	
	public VehiculoParqueadero findByVehiculoActivo(Vehiculo vehiculo) {
		return vehiculoParqueaderoDao.findByVehiculoAndFechaEgresoIsNull(vehiculo).orElse(null);
	}
	
	public List<VehiculoParqueadero> listarVehiculosActivosEnParquadero(Parqueadero parqueadero) {
//		vehiculoParqueaderoDao.findByPlaca(Vehiculo vehiculo);
		return vehiculoParqueaderoDao.findAllByParqueaderoAndFechaEgresoIsNull(parqueadero);
	}

}
