package persistencia.sistema;

import javax.persistence.EntityManager;

import dominio.repositorio.RepositorioVehiculo;
import persistencia.conexion.ConexionJPA;
import persistencia.repositorio.RepositorioVehiculoPersistente;


public class SistemaDePersistencia {
	private EntityManager entityManager;

	public SistemaDePersistencia() {
		this.entityManager = new ConexionJPA().createEntityManager();
	}

	public RepositorioVehiculo obtenerRepositorioVehiculo() {
		return new RepositorioVehiculoPersistente(entityManager);
	}
	
	/*public RepositorioPrestamo obtenerRepositorioPrestamos() {
		return new RepositorioPrestamoPersistente(entityManager, this.obtenerRepositorioVehiculo());
	}*/

	public void iniciar() {
		entityManager.getTransaction().begin();
	}

	public void terminar() {
		entityManager.getTransaction().commit();
	}
}
