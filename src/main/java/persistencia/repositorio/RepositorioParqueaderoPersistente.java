package persistencia.repositorio;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import dominio.Parqueadero;
import dominio.repositorio.RepositorioParqueadero;
import persistencia.builder.ParqueaderoBuilder;
import persistencia.entidad.ParqueaderoEntity;

public class RepositorioParqueaderoPersistente implements RepositorioParqueadero {
	
	private static final String PLACA = "placa";
	private static final String PARQUEADERO_FIND_BY_PLACA = "Parqueadero.findByPlaca";
	//private static final String VEHICULO_FIND_VEHICULOS = "Vehiculo.findCountVehiculos";

	private EntityManager entityManager;

	public RepositorioParqueaderoPersistente(EntityManager entityManager) {
		
		this.entityManager = entityManager;
	}	
	
	@Override
	public ParqueaderoEntity obtenerParqueaderoEntityPorPlaca(String placa) {
		
		Query query = entityManager.createNamedQuery(PARQUEADERO_FIND_BY_PLACA);
		query.setParameter(PLACA, placa);

		return (ParqueaderoEntity) query.getSingleResult();
	}

	@Override
	public Parqueadero obtenerPorPlaca(String placa) {
		
		ParqueaderoEntity parqueaderoEntity = obtenerParqueaderoEntityPorPlaca(placa);
		return ParqueaderoBuilder.convertirADominio(parqueaderoEntity);
	
	}

	@Override
	public void agregar(ParqueaderoEntity parqueaderoEntity) {		
		entityManager.getTransaction().begin();
		entityManager.persist(parqueaderoEntity);
		entityManager.getTransaction().commit();
	}

}
