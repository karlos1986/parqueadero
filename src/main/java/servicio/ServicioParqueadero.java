package servicio;


import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import dominio.Carro;
import dominio.Moto;
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

	public Response ingresarVehiculo(String placa, int tipo, int cilindraje) {
		try {
			Vigilante vigilante = new Vigilante(repositorioVehiculo, repositorioParqueadero);
			if (tipo == 1) {
				Carro carro = new Carro(placa, tipo);
				vigilante.registrarVehiculo(carro);
				return Response.status(201).build();
			}
			if (tipo == 2) {
				Moto moto = new Moto(placa, tipo, cilindraje);
				vigilante.registrarVehiculo(moto);
				return Response.status(201).build();			
				}
		} catch (IngresarExcepcion e) {
			 return Response.status(403).entity(e.getMessage()).build();
		}
		return Response.status(400).build();
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


