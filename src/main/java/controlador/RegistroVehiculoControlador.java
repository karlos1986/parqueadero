package controlador;

import java.util.List;

import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import dominio.Parqueadero;
import servicio.ServicioIngresar;

@Component
@RestController
public class RegistroVehiculoControlador {
	
	@Autowired
	private ServicioIngresar servicioIngresar;

	public RegistroVehiculoControlador(ServicioIngresar servicioIngresar) {
	        this.servicioIngresar = servicioIngresar;
	    }
	
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(method = RequestMethod.POST, path = "/ingreso")
	public Response ingreso(@RequestParam(value = "placa", required = true) String placa,
			@RequestParam(value = "tipo", required = true) int tipo,
			@RequestParam(value = "cilindraje", required = false, defaultValue = "0") int cilindraje) {
		return Response.status(200).build();	
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping("/pago")
	public Response pago(@RequestParam(value = "placa", required = true) String placa){
		return Response.status(200).entity("El Valor Es: "+ servicioIngresar.salidaVehiculos(placa)+" $").build();
		
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping("/consulta")
	public List<Parqueadero> consulta(){
		return servicioIngresar.listaDeVehiculos();	
	}
	
}