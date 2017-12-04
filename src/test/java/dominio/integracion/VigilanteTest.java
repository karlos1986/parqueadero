package dominio.integracion;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

import javax.persistence.EntityManager;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import dominio.Carro;
import dominio.Moto;
import dominio.repositorio.RepositorioVehiculo;
import persistencia.repositorio.RepositorioVehiculoPersistente;
import persistencia.sistema.SistemaDePersistencia;
import testdatabuilder.CarroTestDataBuilder;
import testdatabuilder.MotoTestDataBuilder;


public class VigilanteTest {
	
	private static final String PLACA = "AAA000";
	private static final int TIPO_CARRO = 1;
	private static final int TIPO_MOTO = 2;
	private static final int CILINDRAJE_MOTO = 600;


	
	private SistemaDePersistencia sistemaPersistencia;
	private RepositorioVehiculo repositorioVehiculo;

	@Before
	public void setUp() {
		
		sistemaPersistencia = new SistemaDePersistencia();
		repositorioVehiculo = sistemaPersistencia.obtenerRepositorioVehiculo();
		sistemaPersistencia.iniciar();
	}
	
	@After
	public void tearDown() {
		sistemaPersistencia.terminar();
	}

	@Test
	public void ingresarCarroBDTest() {

		// arrange
		Carro carro = new CarroTestDataBuilder().conPlaca(PLACA).conTipo(TIPO_CARRO).build();
		
		// act
		repositorioVehiculo.agregar(carro);
		carro = (Carro) repositorioVehiculo.obtenerPorPlaca(PLACA);
		

		// assert
		assertEquals(PLACA, carro.getPlaca());
		assertEquals(TIPO_CARRO, carro.getTipo());
		
	}
	
	@Test
	public void ingresarMotoBDTest() {

		// arrange
		Moto moto = new MotoTestDataBuilder().conPlaca(PLACA).conTipo(TIPO_MOTO).conCilindraje(CILINDRAJE_MOTO).build();
		
		// act
		repositorioVehiculo.agregar(moto);
		moto = (Moto) repositorioVehiculo.obtenerPorPlaca(PLACA);
		

		// assert
		assertEquals(PLACA, moto.getPlaca());
		assertEquals(TIPO_MOTO, moto.getTipo());
		assertEquals(CILINDRAJE_MOTO, moto.getCilindraje());		
		
	}
	
	

}
