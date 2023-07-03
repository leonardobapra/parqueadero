package com.bolsadeideas.springboot.form.app.dao;



import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bolsadeideas.springboot.form.app.entity.Vehiculo;

@Repository
public interface IVehiculoDao extends JpaRepository<Vehiculo, Long> {
	Optional<Vehiculo> findByPlaca(String placa);
//	@Query("select p from usuario p where p.email like %?1%")
//	public Usuario findByEmail(String email);
	
}
