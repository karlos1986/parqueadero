package servicio;


import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import dominio.Carro;
import dominio.Moto;
import dominio.Parqueadero;
import dominio.Vehiculo;
import dominio.Vigilante;
import dominio.excepcion.IngresarExcepcion;
import dominio.repositorio.RepositorioParqueadero;
import dominio.repositorio.RepositorioVehiculo;
import persistencia.sistema.SistemaDePersistencia;
import repuestas.Recibo;

@Component
@Service
public class ServicioIngresar {
	
	public static final String EL_VEHICULO_SE_ENCUENTRA_EN_EL_PARQUEADERO = "El Vehiculo Se Encuentra En El Parqueadero";
	public static final String INGRESO_CORRECTO = "Ingreso Correcto";


	@Autowired
	private SistemaDePersistencia sistemaPersistencia;
	private RepositorioParqueadero repositorioParqueadero;
	private RepositorioVehiculo repositorioVehiculo;
	

	public ServicioIngresar() {
		this.sistemaPersistencia = SistemaDePersistencia.getInstance();
		this.repositorioParqueadero = sistemaPersistencia.obtenerRepositorioParqueadero();
		this.repositorioVehiculo = sistemaPersistencia.obtenerRepositorioVehiculo();
	}

	public Recibo ingresarVehiculo(String placa, int tipo, int cilindraje) {
		try {
			Vigilante vigilante = new Vigilante(repositorioVehiculo, repositorioParqueadero);
			if(repositorioParqueadero.obtenerParqueaderoEntity(placa) != null) {
				return new Recibo(null,EL_VEHICULO_SE_ENCUENTRA_EN_EL_PARQUEADERO);
			}
			if (tipo == 1) {
				Carro carro = new Carro(placa, tipo);
				vigilante.registrarVehiculo(carro);
				return new Recibo(carro,INGRESO_CORRECTO);
			}
			if (tipo == 2) {
				Moto moto = new Moto(placa, tipo, cilindraje);
				vigilante.registrarVehiculo(moto);
				return new Recibo(moto,INGRESO_CORRECTO);
			}
			return new Recibo(null,"No Se Ingreso El Vehiculo");
		} catch (IngresarExcepcion e) {
			return new Recibo(null,e.getMessage());
		}	
	}

	public double salidaVehiculos(String placa) {
		try {
			Vigilante vigilante = new Vigilante(repositorioVehiculo, repositorioParqueadero);
			Vehiculo vehiculo = repositorioVehiculo.obtenerPorPlaca(placa);
			return (vehiculo == null)? 0.00 : vigilante.salidaVehiculo(vehiculo);
		}catch(NoResultException e) {
			return 0.00;
		}	
	}
	
	public List<Parqueadero> listaDeVehiculos(){
		Vigilante vigilante = new Vigilante(repositorioVehiculo, repositorioParqueadero);
		return vigilante.consultarParqueadero();
	}
}


