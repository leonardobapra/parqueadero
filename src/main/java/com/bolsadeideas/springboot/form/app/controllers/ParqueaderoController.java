package com.bolsadeideas.springboot.form.app.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bolsadeideas.springboot.form.app.entity.Parqueadero;
import com.bolsadeideas.springboot.form.app.entity.Usuario;
import com.bolsadeideas.springboot.form.app.security.UserDetailServiceImpl;
import com.bolsadeideas.springboot.form.app.service.ParqueaderoService;
import com.bolsadeideas.springboot.form.app.service.UsuarioService;

@RestController
@RequestMapping("/parqueaderos")
public class ParqueaderoController {
    @Autowired
    private ParqueaderoService parqueaderoService;
    @Autowired
    private UserDetailServiceImpl userDetailServiceImpl;
    @Autowired
    private UsuarioService usuarioService;

	@PostMapping("/crearParqueadero")
	public ResponseEntity<String> crearParqueadero(@RequestBody Parqueadero parqueadero) {
		
		String rolUserLoad = userDetailServiceImpl.getNombreRol();
		if (!"ADMIN".equals(rolUserLoad)) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("NO autorizado");
		}

		Parqueadero existeParquedero = parqueaderoService.findByRegistro(parqueadero.getRegistro());
		if (existeParquedero != null) {
			return ResponseEntity.badRequest().body("El parqueadero ya existe");
		}

		parqueaderoService.save(parqueadero);
		return ResponseEntity.ok("parqueadero creado exitosamente");

	}
	
	@GetMapping("/listar")
	public ResponseEntity<List<Parqueadero>> listarUsuarios() {

		List<Parqueadero> parqueaderos = parqueaderoService.findAll();

		return ResponseEntity.ok(parqueaderos);
	}
	
	@DeleteMapping("/eliminar")
    public ResponseEntity<String> eliminarUsuario(@RequestParam("id") Long id) {
		String rolUserLoad = userDetailServiceImpl.getNombreRol();
		if (!"ADMIN".equals(rolUserLoad)) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("NO autorizado");
		}
		parqueaderoService.delete(id);
        return ResponseEntity.ok("Parqueadero eliminado exitosamente");
    }
	
	@PutMapping("/actualizar")
    public ResponseEntity<String> actualizarUsuario(@RequestBody Parqueadero parqueadero) {
		String rolUserLoad = userDetailServiceImpl.getNombreRol();
		if (!"ADMIN".equals(rolUserLoad)) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("NO autorizado");
		}
		Parqueadero existeParquedero = parqueaderoService.findByRegistro(parqueadero.getRegistro());
		if (existeParquedero != null) {
			parqueaderoService.update(parqueadero);
	        return ResponseEntity.ok("Parqueadero actualizado exitosamente");
		} else {
			return ResponseEntity.badRequest().body("El Parqueadero NO existe");
		}
		
    }
	
	@PutMapping("/asociarPaqueadero")
    public ResponseEntity<String> asociarParqueadero(@RequestParam("idParqueadero") Long idParqueadero, @RequestParam("idUsuario") Long idUsuario) {
		String rolUserLoad = userDetailServiceImpl.getNombreRol();
		if (!"ADMIN".equals(rolUserLoad)) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("NO autorizado");
		}
		Parqueadero parqueadero = parqueaderoService.findById(idParqueadero);
		if (parqueadero == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El Parqueadero NO existe");
		}
		if (parqueadero.getUsuario() != null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El Parqueadero ya posee un socio");
		}
		Usuario usuario = usuarioService.findById(idUsuario);
		if (usuario == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El usuario NO existe");
		}
		if (!"SOCIO".equals(usuario.getRol().getNombre())){
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El usuario NO es un Socio");
		}
		parqueadero.setUsuario(usuario);
		parqueaderoService.update(parqueadero);
	    return ResponseEntity.ok("Usuario Asociado al Parqueadero exitosamente");
		
		
    }
}