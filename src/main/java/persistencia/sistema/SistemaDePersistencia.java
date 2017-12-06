package persistencia.sistema;

import javax.persistence.EntityManager;

import dominio.repositorio.RepositorioParqueadero;
import dominio.repositorio.RepositorioVehiculo;
import persistencia.conexion.ConexionJPA;
import persistencia.repositorio.RepositorioParqueaderoPersistente;
import persistencia.repositorio.RepositorioVehiculoPersistente;


public class SistemaDePersistencia {
	private EntityManager entityManager;
	
	private static SistemaDePersistencia sistemaPersistencia;
	
	public static SistemaDePersistencia getInstance() {
		if(sistemaPersistencia == null) {
			sistemaPersistencia = new SistemaDePersistencia();
		}
		return sistemaPersistencia;
	}

	private SistemaDePersistencia() {
		this.entityManager = new ConexionJPA().createEntityManager();
	}

	public RepositorioVehiculo obtenerRepositorioVehiculo() {
		return new RepositorioVehiculoPersistente(entityManager);
	}
	
	public RepositorioParqueadero obtenerRepositorioParqueadero() {
		return new RepositorioParqueaderoPersistente(entityManager);
	}

	public void iniciar() {
		entityManager.getTransaction().begin();
	}

	public void terminar() {
		entityManager.getTransaction().commit();
	}
}
