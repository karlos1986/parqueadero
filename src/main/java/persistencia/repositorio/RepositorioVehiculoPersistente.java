package persistencia.repositorio;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import dominio.Vehiculo;
import dominio.repositorio.RepositorioVehiculo;
import persistencia.builder.VehiculoBuilder;
import persistencia.entidad.VehiculoEntity;
import persistencia.repositorio.jpa.RepositorioVehiculoJPA;

public class RepositorioVehiculoPersistente implements RepositorioVehiculo, RepositorioVehiculoJPA{
	
	private static final String PLACA = "placa";
	private static final String VEHICULO_FIND_BY_PLACA = "Vehiculo.findByPlaca";
	private static final String VEHICULO_FIND_VEHICULOS = "Vehiculo.findCountVehiculos";
	private static final String DELETE_VEHICULO = "delete Vehiculo";
	private static final String VEHICULOS_FIND = "Vehiculo.findVehiculos";


	private EntityManager entityManager;

	public RepositorioVehiculoPersistente(EntityManager entityManager) {
		
		this.entityManager = entityManager;
	}	


	@Override
	public Vehiculo obtenerPorPlaca(String placa) {
		try {
			VehiculoEntity vehiculoEntity = obtenerVehiculoEntityPorPlaca(placa);
			return VehiculoBuilder.convertirADominio(vehiculoEntity);
		} catch (NoResultException e) {
			return null;
		}

	}

	@Override
	public void agregar(Vehiculo vehiculo) {
		if(obtenerPorPlaca(vehiculo.getPlaca()) == null) {
			entityManager.getTransaction().begin();
			entityManager.persist(VehiculoBuilder.convertirAEntity(vehiculo));
			entityManager.getTransaction().commit();
		}
	}

	@Override
	public VehiculoEntity obtenerVehiculoEntityPorPlaca(String placa) {
		
		Query query = entityManager.createNamedQuery(VEHICULO_FIND_BY_PLACA);
		query.setParameter(PLACA, placa);

		return (VehiculoEntity) query.getSingleResult();	
	}
	
	@Override
	public int obtenerCountVehiculos() {
		
		Query query = entityManager.createNamedQuery(VEHICULO_FIND_VEHICULOS);
		return (int) query.getSingleResult();
	}


	@Override
	public void borrarContenidoTablaVehiculo() {
		entityManager.getTransaction().begin();
		entityManager.createNativeQuery(DELETE_VEHICULO).executeUpdate();
		entityManager.getTransaction().commit();
	}


	@Override
	public List<Vehiculo> consultarVehiculos() {
		Query query = entityManager.createNamedQuery(VEHICULOS_FIND);

		return query.getResultList();
	}
}
