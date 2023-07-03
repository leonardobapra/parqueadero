package com.bolsadeideas.springboot.form.app.dao;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bolsadeideas.springboot.form.app.entity.Parqueadero;

@Repository
public interface IParqueaderoDao extends JpaRepository<Parqueadero, Long> {
	Optional<Parqueadero> findByRegistro(String registro);
//	@Query("select p from usuario p where p.email like %?1%")
//	public Usuario findByEmail(String email);
	
}
