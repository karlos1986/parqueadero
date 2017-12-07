package dominio.repositorio;

import dominio.Vehiculo;
import persistencia.entidad.VehiculoEntity;

public interface RepositorioVehiculo {
	
	
	Vehiculo obtenerPorPlaca(String placa);

	void agregar(Vehiculo vehiculo);
	
	VehiculoEntity obtenerVehiculoEntityPorPlaca(String placa);
	
	void borrarContenidoTablaVehiculo();
	
	
}
