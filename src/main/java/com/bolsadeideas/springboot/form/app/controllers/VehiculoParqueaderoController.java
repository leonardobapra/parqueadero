package com.bolsadeideas.springboot.form.app.controllers;

import java.time.LocalDateTime;
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
import com.bolsadeideas.springboot.form.app.entity.VehiculoParqueadero;
import com.bolsadeideas.springboot.form.app.service.ParqueaderoService;
import com.bolsadeideas.springboot.form.app.service.VehiculoParqueaderoService;
import com.bolsadeideas.springboot.form.app.service.VehiculoService;

@RestController
@RequestMapping("/vehiculosParqueadero")
public class VehiculoParqueaderoController {
    @Autowired
    private VehiculoParqueaderoService vehiculoParqueaderoService;
    @Autowired
    private ParqueaderoService parqueaderoService;

	@PostMapping("/ingresarVehiculo")
	public ResponseEntity<String> IngresarVehiculo(@RequestBody VehiculoParqueadero vehiculoParqueadero) {
		
		VehiculoParqueadero existeVehiculo = vehiculoParqueaderoService.findByVehiculoActivo(vehiculoParqueadero.getVehiculo());
		if (existeVehiculo != null) {
			return ResponseEntity.badRequest().body("El vehiculo ya esta en el parqueadero");
		}
		Parqueadero parqueadero = parqueaderoService.findById(vehiculoParqueadero.getParqueadero().getId());
		 
		int cantidaPuestos = parqueadero.getCantidadPuestos();
		
		List<VehiculoParqueadero> listaVehiculosParqueadero = vehiculoParqueaderoService.listarVehiculosActivosEnParquadero(parqueadero);
		if (listaVehiculosParqueadero.size() < cantidaPuestos) {
			vehiculoParqueaderoService.save(vehiculoParqueadero);
			return ResponseEntity.ok("vehiculo Ingresado exitosamente");
		} else {
			return ResponseEntity.badRequest().body("El parqueadero esta lleno");
		}
		
		

	}
	
	@GetMapping("/listarPorParqueadero")
	public ResponseEntity<List<VehiculoParqueadero>> listarVehiculos(@RequestParam("id") Long id) {

		Parqueadero parqueadero = parqueaderoService.findById(id);
		
		List<VehiculoParqueadero> listaVehiculosParqueadero = vehiculoParqueaderoService.listarVehiculosActivosEnParquadero(parqueadero);

		return ResponseEntity.ok(listaVehiculosParqueadero);
	}
	
	@PutMapping("/egresarVehiculo")
    public ResponseEntity<String> sacarVehiculo(@RequestBody VehiculoParqueadero vehiculoParqueadero) {
		
		VehiculoParqueadero existeVehiculo = vehiculoParqueaderoService.findByVehiculoActivo(vehiculoParqueadero.getVehiculo());
		if (existeVehiculo != null) {
			existeVehiculo.setFechaEgreso(LocalDateTime.now());
			vehiculoParqueaderoService.update(existeVehiculo);
	        return ResponseEntity.ok("vehiculo salio del parqueadero exitosamente");
		} else {
			return ResponseEntity.badRequest().body("El vehiculo no esta en el parqueadero");
		}
		
    }
//	
//	@PutMapping("/actualizar")
//    public ResponseEntity<String> actualizarVehiculo(@RequestBody Vehiculo vehiculo) {
//		
//		Optional<Vehiculo> existeVehiculo= vehiculoService.findByPlaca(vehiculo.getPlaca());
//		if (existeVehiculo.isPresent()) {
//			vehiculoService.update(vehiculo);
//	        return ResponseEntity.ok("Vehiculo actualizado exitosamente");
//		} else {
//			return ResponseEntity.badRequest().body("El Vehiculo NO existe");
//		}
//		
//    }
}