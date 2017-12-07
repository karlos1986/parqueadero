package dominio.repositorio;

import dominio.Parqueadero;
import persistencia.entidad.ParqueaderoEntity;

public interface RepositorioParqueadero {
	
	
	Parqueadero obtenerPorPlaca(String placa);
	
	ParqueaderoEntity obtenerParqueaderoEntityPorPlaca(String placa);
	
	ParqueaderoEntity obtenerParqueaderoEntity(String placa);

	void agregar(ParqueaderoEntity parqueaderoEntity);
	
	int consultarCarrosEnElParqueadero();
	
	int consultarMotosEnElParqueados();
	
	void registrarSalidaVehiculo();
	
	public void borrarContenidoTablaParqueadero() ;
}
