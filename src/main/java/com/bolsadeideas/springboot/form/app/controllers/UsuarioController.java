package com.bolsadeideas.springboot.form.app.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.bolsadeideas.springboot.form.app.entity.Socio;
import com.bolsadeideas.springboot.form.app.entity.SocioCliente;
import com.bolsadeideas.springboot.form.app.entity.Usuario;
import com.bolsadeideas.springboot.form.app.security.UserDetailServiceImpl;
import com.bolsadeideas.springboot.form.app.security.UserDetailsImpl;
import com.bolsadeideas.springboot.form.app.service.SocioClienteService;
import com.bolsadeideas.springboot.form.app.service.SocioService;
import com.bolsadeideas.springboot.form.app.service.UsuarioService;

import ch.qos.logback.core.status.Status;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private SocioService socioService;
    @Autowired
    private UserDetailServiceImpl userDetailServiceImpl;
    @Autowired
    private SocioClienteService socioClienteService;

    @PostMapping("/crearUsuario")
    public ResponseEntity<String> crearUsuario(@RequestBody Usuario usuario) {
        // Verificar si el usuario actual tiene el rol "ADMIN"
    	// Obtener la autenticación actual
        String rolUserLoad = userDetailServiceImpl.getNombreRol();
    
        // Crear un nuevo usuario
    	Optional<Usuario> existeUser = usuarioService.findByEmail(usuario.getEmail());
    	if (existeUser.isPresent()) {
            return ResponseEntity.badRequest().body("El usuario ya existe");
        }
    	String password = usuario.getPassword();
    	usuario.setPassword(new BCryptPasswordEncoder().encode(password));
        
        if (usuario.getRol().getId() == 3 && "ADMIN".equals(rolUserLoad)) {
        	usuarioService.save(usuario);
        	Socio socio = new Socio();
        	socio.setUsuario(usuario);
        	socioService.save(socio);
        	return ResponseEntity.ok("Usuario Socio creado exitosamente");
        } else if (usuario.getRol().getId() == 2  && !"CLIENTE".equals(rolUserLoad)) {
        	usuarioService.save(usuario);
        	return ResponseEntity.ok("Usuario Cliente creado exitosamente");
        } else if (usuario.getRol().getId() == 1 || usuario.getRol().getId() > 3) {
        	return ResponseEntity.badRequest().body("Rol no permitido");
        } 
        return  ResponseEntity.status(HttpStatus.FORBIDDEN).body("NO autorizado");
    }
    
    @GetMapping("/listar")
	public ResponseEntity<List<Usuario>> listarUsuarios() {

		List<Usuario> usuarios = usuarioService.findAll();

		return ResponseEntity.ok(usuarios);
	}
    
    @PostMapping("/asociarClienteSocio")
    public ResponseEntity<String> asociarClienteSocio(@RequestParam("idSocio") Long idSocio, @RequestParam("idCliente") Long idCliente) {

    	UserDetailsImpl userLoad = (UserDetailsImpl) userDetailServiceImpl.loadUser();
        if ("CLIENTE".equals(userLoad.getNombreRol())) {
        	 return  ResponseEntity.status(HttpStatus.FORBIDDEN).body("NO autorizado");
        }
        Usuario usuarioSocio = usuarioService.findById(idSocio);
        Usuario usuarioCliente = usuarioService.findById(idCliente);
        if ("SOCIO".equals(userLoad.getNombreRol())) {
        	if(userLoad.getIdUsuario() != usuarioSocio.getId()){
        		return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("NO autorizado solo puede Asociar clientes a si mismo ");
        	}
        }
        if (usuarioSocio == null || !"SOCIO".equals(usuarioSocio.getRol().getNombre())) {
        	return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("el id Socio proporcionado no es un socio o no existe");
        }
        if (usuarioCliente == null || !"CLIENTE".equals(usuarioCliente.getRol().getNombre())) {
        	return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("el id Cliente proporcionado no es un cliente o no existe");
        }
        
    	SocioCliente socioCliente = new SocioCliente();
    	socioCliente.setUsuarioCliente(usuarioCliente);
    	socioCliente.setUsuarioSocio(usuarioSocio);
    	try {
    		socioClienteService.save(socioCliente);
		} catch (Exception e) {
			return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("el cliente ya esta asociado a este socio");
		}
        return ResponseEntity.ok("usuario asociado exitosamente");
    }
}