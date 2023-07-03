package com.bolsadeideas.springboot.form.app.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.bolsadeideas.springboot.form.app.entity.SocioCliente;

@Repository
public interface ISocioClienteDao extends JpaRepository<SocioCliente, Long> {
//	@Query("select p from usuario p where p.email like %?1%")
//	public Usuario findByEmail(String email);
	
}
