package controlador;

import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dominio.Carro;
import dominio.Moto;
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
	@RequestMapping(method = RequestMethod.POST, path = "/ingreso/carro",consumes="application/json",produces = "application/json")
	public Response ingresoCarro(@RequestBody Carro carro) {
		return servicioParqueadero.ingresarVehiculo(carro);	
	}
	
	@CrossOrigin()
	@RequestMapping(method = RequestMethod.POST, path = "/ingreso/moto",consumes="application/json",produces = "application/json")
	public Response ingresoMoto(@RequestBody Moto moto) {
		return servicioParqueadero.ingresarVehiculo(moto);	
	}
	
	@CrossOrigin()
	@RequestMapping("/pago")
	public Response pago(@RequestParam(value = "placa", required = true) String placa){
		return servicioParqueadero.salidaVehiculos(placa);
		
	}
	
	@CrossOrigin()
	@RequestMapping(method = RequestMethod.GET ,path ="/consulta")
	public Response consulta(){
		return servicioParqueadero.listaDeVehiculos();
	}
	
}