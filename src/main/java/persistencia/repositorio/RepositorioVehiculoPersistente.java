package persistencia.repositorio;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import dominio.Vehiculo;
import dominio.repositorio.RepositorioVehiculo;
import persistencia.builder.VehiculoBuilder;
import persistencia.entidad.VehiculoEntity;
import persistencia.repositorio.jpa.RepositorioVehiculoJPA;

public class RepositorioVehiculoPersistente implements RepositorioVehiculo, RepositorioVehiculoJPA{
	
	private static final String PLACA = "AAA000";
	private static final String VEHICULO_FIND_BY_PLACA = "Vehiculo.findByPlaca";
	private EntityManager entityManager;

	public RepositorioVehiculoPersistente(EntityManager entityManager) {
		
		this.entityManager = entityManager;
	}	


	@Override
	public Vehiculo obtenerPorPlaca(String placa) {
		
		VehiculoEntity vehiculoEntity = obtenerVehiculoEntityPorPlaca(placa);

		return VehiculoBuilder.convertirADominio(vehiculoEntity);
	}

	@Override
	public void agregar(Vehiculo vehiculo) {
		
		entityManager.persist(VehiculoBuilder.convertirAEntity(vehiculo));
	}

	@Override
	public VehiculoEntity obtenerVehiculoEntityPorPlaca(String placa) {
		
		Query query = entityManager.createNamedQuery(VEHICULO_FIND_BY_PLACA);
		query.setParameter(PLACA, placa);

		return (VehiculoEntity) query.getSingleResult();
	}


}
