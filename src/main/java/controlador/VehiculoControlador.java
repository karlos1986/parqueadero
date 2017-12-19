package controlador;

import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import servicio.ServicioParqueadero;

@Component
@RestController
public class VehiculoControlador {
	
	@Autowired
	private ServicioParqueadero servicioParqueadero;

	public VehiculoControlador(ServicioParqueadero servicioIngresar) {
	        this.servicioParqueadero = servicioIngresar;
	    }
	
	@CrossOrigin()
	@RequestMapping(method = RequestMethod.POST, path = "/ingreso")
	public Response ingreso(@RequestParam(value = "placa", required = true) String placa,
			@RequestParam(value = "tipo", required = true) int tipo,
			@RequestParam(value = "cilindraje", required = false, defaultValue = "0") int cilindraje) {
		return servicioParqueadero.ingresarVehiculo(placa, tipo, cilindraje);	
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping("/pago")
	public Response pago(@RequestParam(value = "placa", required = true) String placa){
		return servicioParqueadero.salidaVehiculos(placa);
		
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping("/consulta")
	public Response consulta(){
		return servicioParqueadero.listaDeVehiculos();
	}
	
}