package persistencia.repositorio;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import dominio.Parqueadero;
import dominio.repositorio.RepositorioParqueadero;
import persistencia.builder.ParqueaderoBuilder;
import persistencia.entidad.ParqueaderoEntity;

public class RepositorioParqueaderoPersistente implements RepositorioParqueadero {
	
	private static final String PLACA = "placa";
	private static final String PARQUEADERO_FIND_BY_PLACA = "Parqueadero.findByPlaca";
	private static final String PARQUEADERO_FIND_CARROS = "Parqueadero.findCeldasCarro";
	private static final String PARQUEADERO_FIND_MOTOS = "Parqueadero.findCeldasMoto";
	private static final String DELETE_PARQUEADERO = "delete Parqueadero";
	private static final String PARQUEADERO_FIND_BY_VEHICULO= "Parqueadero.findForVehiculo";

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
	
	@Override
	public int consultarMotosEnElParqueados() {
		Query query = entityManager.createNamedQuery(PARQUEADERO_FIND_MOTOS);
		Long result =  (Long) query.getSingleResult();
		return result.intValue();
		
	}
	
	@Override
	public int consultarCarrosEnElParqueadero() {
		Query query = entityManager.createNamedQuery(PARQUEADERO_FIND_CARROS);
		Long result =  (Long) query.getSingleResult();
		return result.intValue();
		
	}

	@Override
	public void borrarContenidoTablaParqueadero() {
		entityManager.getTransaction().begin();
		entityManager.createNativeQuery(DELETE_PARQUEADERO).executeUpdate();
		entityManager.getTransaction().commit();
	}

	@Override
	public ParqueaderoEntity obtenerParqueaderoEntity(String placa) {
		Query query = entityManager.createNamedQuery(PARQUEADERO_FIND_BY_VEHICULO);
		query.setParameter(PLACA, placa);
		return (ParqueaderoEntity) query.getSingleResult();
	}

	@Override
	public void registrarSalidaVehiculo(String placa,Date date) {
		entityManager.getTransaction().begin();
		ParqueaderoEntity parqueaderoEntity = obtenerParqueaderoEntityPorPlaca(placa);
		parqueaderoEntity.setDateEgreso(date);
		entityManager.merge(parqueaderoEntity);
		entityManager.getTransaction().commit();
	}

}
