package dominio.repositorio;

import dominio.Vehiculo;

public interface RepositorioVehiculo {
	
	/**
	 * Permite obtener un vehiculo dada una placa
	 * @param isbn
	 * @return
	 */
	Vehiculo obtenerPorPlaca(String placa);

	/**
	 * Permite agregar un libro al repositorio
	 * @param libro
	 */
	void agregar(Vehiculo vehiculo);


}
