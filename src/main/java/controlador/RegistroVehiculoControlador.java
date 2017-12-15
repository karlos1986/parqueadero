package controlador;

import java.util.List;

import javax.ws.rs.OPTIONS;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dominio.Moto;
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
	
	@RequestMapping("/ingreso")
	public String ingreso(@RequestParam(value = "placa", required = true) String placa,
			@RequestParam(value = "tipo", required = true) int tipo,
			@RequestParam(value = "cilindraje", required = false, defaultValue = "0") int cilindraje) {
		//return Response.status(200).entity(servicioIngresar.ingresarVehiculo(placa, tipo, cilindraje)).build();	
		return servicioIngresar.ingresarVehiculo(placa, tipo, cilindraje);
	}
	
	@RequestMapping("/pago")
	public String pago(@RequestParam(value = "placa", required = true) String placa){
		String output = "El Valor Es: "+ servicioIngresar.salidaVehiculos(placa)+" $";
		return output;
		//return Response.status(200).entity(output).build();
		/*return Response.ok(output)
			      .header("Access-Control-Allow-Origin", "http://evil.com/")
			      .header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS")
			      .header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With").build();*/
	}
	
	@OPTIONS
	@RequestMapping("/consulta")
	public List<Parqueadero> consulta(){
		return servicioIngresar.listaDeVehiculos();
		//return Response.ok((servicioIngresar.listaDeVehiculos())).header("Access-Control-Allow-Origin", "*").build();
		//return Response.status(200).entity(servicioIngresar.listaDeVehiculos()).build();	
		/*return Response.ok((servicioIngresar.listaDeVehiculos()))
			      .header("Access-Control-Allow-Origin", "http://evil.com/")
			      .header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS")
			      .header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With").build();*/
	}
	
}