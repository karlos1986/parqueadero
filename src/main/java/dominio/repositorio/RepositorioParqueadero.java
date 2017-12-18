package dominio.repositorio;

import java.util.Date;
import java.util.List;

import dominio.Parqueadero;
import persistencia.entidad.ParqueaderoEntity;

public interface RepositorioParqueadero {
	
	
	Parqueadero obtenerPorPlaca(String placa);
	
	ParqueaderoEntity obtenerParqueaderoEntityPorPlaca(String placa);
	
	ParqueaderoEntity obtenerParqueaderoEntity(String placa);

	void agregar(ParqueaderoEntity parqueaderoEntity);
	
	int consultarCarrosEnElParqueadero();
	
	int consultarMotosEnElParqueados();
	
	void registrarSalidaVehiculo(String placa,Date date);
	
	public void borrarContenidoTablaParqueadero() ;
	
	public List<Parqueadero> consultarParqueadero();
}
