package com.bolsadeideas.springboot.form.app.dao;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bolsadeideas.springboot.form.app.entity.Parqueadero;
import com.bolsadeideas.springboot.form.app.entity.Vehiculo;
import com.bolsadeideas.springboot.form.app.entity.VehiculoParqueadero;

@Repository
public interface IVehiculoParqueaderoDao extends JpaRepository<VehiculoParqueadero, Long> {
//	Optional<Vehiculo> findByPlaca(String placa);
	
//	@Query("select vp from vehiculos_parqueadero vp where vp.fe like %?1%")
	Optional<VehiculoParqueadero> findByVehiculoAndFechaEgresoIsNull(Vehiculo vehiculo);
	
	List<VehiculoParqueadero> findAllByParqueaderoAndFechaEgresoIsNull(Parqueadero parqueadero);
	
}
