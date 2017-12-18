package persistencia.conexion;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ConexionJPA {
	private static final String PARQUEADERO_TEST = "parqueadero-test";
	private EntityManagerFactory entityManagerFactory;

	public ConexionJPA() {
		entityManagerFactory = Persistence.createEntityManagerFactory(PARQUEADERO_TEST);
	}
	
	public EntityManager createEntityManager() {
		return entityManagerFactory.createEntityManager();
	}

}
