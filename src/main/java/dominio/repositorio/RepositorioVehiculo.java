package dominio.repositorio;

import dominio.Vehiculo;
import persistencia.entidad.VehiculoEntity;

public interface RepositorioVehiculo {
	
	/**
	 * Permite obtener un vehiculo dada una placa
	 * @param isbn
	 * @return
	 */
	Vehiculo obtenerPorPlaca(String placa);

	/**
	 * Permite agregar un vehiculo al repositorio
	 * @param vehiculo
	 */
	void agregar(Vehiculo vehiculo);
	
	VehiculoEntity obtenerVehiculoEntityPorPlaca(String placa);


}
