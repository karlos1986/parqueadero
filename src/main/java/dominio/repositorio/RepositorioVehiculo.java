package dominio.repositorio;

import java.util.List;

import dominio.Vehiculo;
import persistencia.entidad.VehiculoEntity;

public interface RepositorioVehiculo {
	
	
	Vehiculo obtenerPorPlaca(String placa);

	void agregar(Vehiculo vehiculo);
	
	VehiculoEntity obtenerVehiculoEntityPorPlaca(String placa);
	
	void borrarContenidoTablaVehiculo();
	
	List<Vehiculo> consultarVehiculos();
	
	
}
