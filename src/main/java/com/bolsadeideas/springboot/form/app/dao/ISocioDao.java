package com.bolsadeideas.springboot.form.app.dao;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bolsadeideas.springboot.form.app.entity.Socio;

@Repository
public interface ISocioDao extends JpaRepository<Socio, Long> {
//	@Query("select p from usuario p where p.email like %?1%")
//	public Usuario findByEmail(String email);
	
}
