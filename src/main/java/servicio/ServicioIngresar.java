package servicio;


import javax.persistence.NonUniqueResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import dominio.Carro;
import dominio.Moto;
import dominio.Vigilante;
import dominio.excepcion.IngresarExcepcion;
import dominio.repositorio.RepositorioParqueadero;
import dominio.repositorio.RepositorioVehiculo;
import persistencia.sistema.SistemaDePersistencia;

@Component
@Service
public class ServicioIngresar {
	
	public static final String El_VEHICULO_SE_ENCUENTRA_EN_EL_PARQUEADERO = "El Vehiculo Se Encuentra En El Parqueadero";

	@Autowired
	private SistemaDePersistencia sistemaPersistencia;
	private RepositorioParqueadero repositorioParqueadero;
	private RepositorioVehiculo repositorioVehiculo;
	

	public ServicioIngresar() {
		this.sistemaPersistencia = SistemaDePersistencia.getInstance();
		this.repositorioParqueadero = sistemaPersistencia.obtenerRepositorioParqueadero();
		this.repositorioVehiculo = sistemaPersistencia.obtenerRepositorioVehiculo();
	}

	public String ingresarVehiculo(String placa, int tipo, int cilindraje) {
		try {
			Vigilante vigilante = new Vigilante(repositorioVehiculo, repositorioParqueadero);
			if (tipo == 1) {
				Carro carro = new Carro(placa, tipo);
				vigilante.registrarVehiculo(carro);
				return "Ingreso Correcto";
			}
			if (tipo == 2) {
				Moto moto = new Moto(placa, tipo, cilindraje);
				vigilante.registrarVehiculo(moto);
				return "Ingreso Correcto";
			}
			return "No Se Ingreso El Vehiculo";
		} catch (IngresarExcepcion e) {
			return e.getMessage();
		}catch(NonUniqueResultException n) {
			return El_VEHICULO_SE_ENCUENTRA_EN_EL_PARQUEADERO;
		}
		
	}
}


