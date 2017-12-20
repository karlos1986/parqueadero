package servicio;


import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import dominio.Vehiculo;
import dominio.Vigilante;
import dominio.excepcion.CalcularCobroExcepcion;
import dominio.excepcion.IngresarExcepcion;
import dominio.repositorio.RepositorioParqueadero;
import dominio.repositorio.RepositorioVehiculo;
import persistencia.sistema.SistemaDePersistencia;

@Component
@Service
public class ServicioParqueadero {
	
	@Autowired
	private SistemaDePersistencia sistemaPersistencia;
	private RepositorioParqueadero repositorioParqueadero;
	private RepositorioVehiculo repositorioVehiculo;
	

	public ServicioParqueadero() {
		this.sistemaPersistencia = SistemaDePersistencia.getInstance();
		this.repositorioParqueadero = sistemaPersistencia.obtenerRepositorioParqueadero();
		this.repositorioVehiculo = sistemaPersistencia.obtenerRepositorioVehiculo();
	}

	public Response ingresarVehiculo(Vehiculo vehiculo) {
		try {
			Vigilante vigilante = new Vigilante(repositorioVehiculo, repositorioParqueadero);
			vigilante.registrarVehiculo(vehiculo);
			return Response.status(201).build();
		} catch (IngresarExcepcion e) {
			return Response.status(403).entity(e.getMessage()).build();
		}
	}

	public Response salidaVehiculos(String placa) {
		try {
			Vigilante vigilante = new Vigilante(repositorioVehiculo, repositorioParqueadero);
			return Response.status(200).entity(vigilante.salidaVehiculo(placa)).build();
		}catch(CalcularCobroExcepcion e) {
			return Response.status(404).entity(e.getMessage()).build();
		}		
	}
	
	public Response listaDeVehiculos(){
		Vigilante vigilante = new Vigilante(repositorioVehiculo, repositorioParqueadero);
		return Response.status(200).entity(vigilante.consultarParqueadero()).build();
	}
}


