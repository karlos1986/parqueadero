package controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import servicio.ServicioIngresar;

@Component
@RestController
public class RegistroVehiculoControlador {
	
	@Autowired
	private ServicioIngresar servicioIngresar;

	public RegistroVehiculoControlador(ServicioIngresar servicioIngresar) {
	        this.servicioIngresar = servicioIngresar;
	    }
	
	@RequestMapping("/ingresar")
	public String ingresar(@RequestParam(value = "placa", required = true) String placa,
			@RequestParam(value = "tipo", required = true) int tipo,
			@RequestParam(value = "cilindraje", required = false, defaultValue = "0") int cilindraje) {
		return(servicioIngresar.ingresarVehiculo(placa, tipo, cilindraje));		
	}
}