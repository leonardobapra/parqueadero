package com.bolsadeideas.springboot.form.app.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "Socio_Cliente", uniqueConstraints = {
	    @UniqueConstraint(columnNames = {"socio_id", "cliente_id"})
	})
public class SocioCliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "socio_id")
    private Usuario usuarioSocio;
    
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Usuario usuarioCliente;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Usuario getUsuarioSocio() {
		return usuarioSocio;
	}

	public void setUsuarioSocio(Usuario usuarioSocio) {
		this.usuarioSocio = usuarioSocio;
	}

	public Usuario getUsuarioCliente() {
		return usuarioCliente;
	}

	public void setUsuarioCliente(Usuario usuarioCliente) {
		this.usuarioCliente = usuarioCliente;
	}
    
    
}