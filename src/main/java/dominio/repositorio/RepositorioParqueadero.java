package dominio.repositorio;

import dominio.Parqueadero;
import persistencia.entidad.ParqueaderoEntity;

public interface RepositorioParqueadero {
	
	/**
	 * Permite obtener un parqueadero dada una placa
	 * @param placa
	 * @return
	 */
	Parqueadero obtenerPorPlaca(String placa);
	
	
	ParqueaderoEntity obtenerParqueaderoEntityPorPlaca(String placa);
	
	void agregar(ParqueaderoEntity parqueaderoEntity);
}
