package com.bolsadeideas.springboot.form.app.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.bolsadeideas.springboot.form.app.entity.Vehiculo;
import com.bolsadeideas.springboot.form.app.service.ParqueaderoService;
import com.bolsadeideas.springboot.form.app.service.VehiculoService;

@RestController
@RequestMapping("/vehiculos")
public class VehiculoController {
    @Autowired
    private VehiculoService vehiculoService;

	@PostMapping("/crear")
	public ResponseEntity<String> crearParqueadero(@RequestBody Vehiculo vehiculo) {
		
		Optional<Vehiculo> existeVehiculo = vehiculoService.findByPlaca(vehiculo.getPlaca());
		if (existeVehiculo.isPresent()) {
			return ResponseEntity.badRequest().body("El vehiculo ya existe");
		}

		vehiculoService.save(vehiculo);
		return ResponseEntity.ok("vehiculo creado exitosamente");

	}
	
	@GetMapping("/listar")
	public ResponseEntity<List<Vehiculo>> listarVehiculos() {

		List<Vehiculo> vehiculos = vehiculoService.findAll();

		return ResponseEntity.ok(vehiculos);
	}
	
	@DeleteMapping("/eliminar")
    public ResponseEntity<String> eliminarVehiculo(@RequestParam("id") Long id) {
		vehiculoService.delete(id);
        return ResponseEntity.ok("vehiculo eliminado exitosamente");
    }
	
	@PutMapping("/actualizar")
    public ResponseEntity<String> actualizarVehiculo(@RequestBody Vehiculo vehiculo) {
		
		Optional<Vehiculo> existeVehiculo= vehiculoService.findByPlaca(vehiculo.getPlaca());
		if (existeVehiculo.isPresent()) {
			vehiculoService.update(vehiculo);
	        return ResponseEntity.ok("Vehiculo actualizado exitosamente");
		} else {
			return ResponseEntity.badRequest().body("El Vehiculo NO existe");
		}
		
    }
}