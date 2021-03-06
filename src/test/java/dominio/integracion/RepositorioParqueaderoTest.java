package dominio.integracion;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dominio.Carro;
import dominio.Moto;
import dominio.ParqueaderoCarro;
import dominio.ParqueaderoMoto;
import dominio.repositorio.RepositorioParqueadero;
import dominio.repositorio.RepositorioVehiculo;
import persistencia.builder.ParqueaderoBuilder;
import persistencia.entidad.ParqueaderoEntity;
import persistencia.sistema.SistemaDePersistencia;

public class RepositorioParqueaderoTest {
	
	private static final String PLACA = "AAA001";
	private static final String PLACA1 = "BBB001";
	private static final String PLACA2 = "BBB002";
	private static final int TIPO_CARRO = 1;
	private static final int TIPO_MOTO = 2;
	private static final int CILINDRAJE_MOTO = 600;

	
	private SistemaDePersistencia sistemaPersistencia;
	private RepositorioParqueadero repositorioParqueadero;
	private RepositorioVehiculo repositorioVehiculo;

	@Before
	public void setUp() {
		
		sistemaPersistencia = SistemaDePersistencia.getInstance();
		repositorioParqueadero = sistemaPersistencia.obtenerRepositorioParqueadero();
		repositorioVehiculo = sistemaPersistencia.obtenerRepositorioVehiculo();
	}
	@After
	public void setDown() {		
		repositorioParqueadero.borrarContenidoTablaParqueadero();
		repositorioVehiculo.borrarContenidoTablaVehiculo();
	}
	
	
	@Test
	public void ingresarParqueaderoCarroBDTest() {

		// arrange
		Carro carro = new Carro(PLACA,TIPO_CARRO);
		repositorioVehiculo.agregar(carro);
		ParqueaderoCarro parqueaderoCarro = new ParqueaderoCarro(carro,new Date());
		ParqueaderoEntity parqueaderoEntity = ParqueaderoBuilder.convertirAEntity(parqueaderoCarro);
		parqueaderoEntity.setVehiculo(repositorioVehiculo.obtenerVehiculoEntityPorPlaca(PLACA));
		ParqueaderoCarro resultParqueaderoCarro = null;
		
		// act 
		repositorioParqueadero.agregar(parqueaderoEntity);
		resultParqueaderoCarro  = (ParqueaderoCarro) repositorioParqueadero.obtenerPorPlaca(carro.getPlaca()) ; 
		
		// assert
		assertEquals(parqueaderoCarro.getDateIngreso(), resultParqueaderoCarro.getDateIngreso());
		assertEquals(parqueaderoCarro.getDateEgreso(), resultParqueaderoCarro.getDateEgreso());
		assertEquals(parqueaderoCarro.getVehiculo().getPlaca(), resultParqueaderoCarro.getVehiculo().getPlaca());
		assertEquals(parqueaderoCarro.getVehiculo().getTipo(), resultParqueaderoCarro.getVehiculo().getTipo());

	}
	@Test
	public void ingresarParqueaderoMotoBDTest() {

		// arrange
		Moto moto = new Moto(PLACA1,TIPO_MOTO,CILINDRAJE_MOTO);
		repositorioVehiculo.agregar(moto);
		ParqueaderoMoto parqueaderoMoto = new ParqueaderoMoto(moto,new Date());
		ParqueaderoEntity parqueaderoEntity = ParqueaderoBuilder.convertirAEntity(parqueaderoMoto);
		parqueaderoEntity.setVehiculo(repositorioVehiculo.obtenerVehiculoEntityPorPlaca(PLACA1));
		ParqueaderoMoto resultParqueaderoMoto = null;
		
		// act 
		repositorioParqueadero.agregar(parqueaderoEntity);
		resultParqueaderoMoto  = (ParqueaderoMoto) repositorioParqueadero.obtenerPorPlaca(moto.getPlaca()) ; 
		
		// assert
		assertEquals(parqueaderoMoto.getDateIngreso(), resultParqueaderoMoto.getDateIngreso());
		assertEquals(parqueaderoMoto.getDateEgreso(), resultParqueaderoMoto.getDateEgreso());
		assertEquals(parqueaderoMoto.getVehiculo().getPlaca(), resultParqueaderoMoto.getVehiculo().getPlaca());
		assertEquals(parqueaderoMoto.getVehiculo().getTipo(), resultParqueaderoMoto.getVehiculo().getTipo());
		
	}
	
	@Test
	public void consultarCarrosParqueadosVacioTest() {

		// arrange
		int carrosParqueados = 0;
		// act 
		carrosParqueados = repositorioParqueadero.consultarCarrosEnElParqueadero();
		// assert
		assertEquals(0,carrosParqueados);
		
	}
	
	@Test
	public void consultarCarrosParqueadosTest() {

		// arrange
		int carrosParqueados = 0;
		Carro carro = new Carro(PLACA1,TIPO_CARRO);
		repositorioVehiculo.agregar(carro);
		ParqueaderoCarro parqueaderoCarro = new ParqueaderoCarro(carro,new Date());
		ParqueaderoEntity parqueaderoEntity = ParqueaderoBuilder.convertirAEntity(parqueaderoCarro);
		parqueaderoEntity.setVehiculo(repositorioVehiculo.obtenerVehiculoEntityPorPlaca(PLACA1));
		repositorioParqueadero.agregar(parqueaderoEntity);
		carro = new Carro(PLACA2,TIPO_CARRO);
		repositorioVehiculo.agregar(carro);
		parqueaderoCarro = new ParqueaderoCarro(carro,new Date());
		parqueaderoEntity = ParqueaderoBuilder.convertirAEntity(parqueaderoCarro);
		parqueaderoEntity.setVehiculo(repositorioVehiculo.obtenerVehiculoEntityPorPlaca(PLACA2));
		repositorioParqueadero.agregar(parqueaderoEntity);
		
		// act 
		carrosParqueados = repositorioParqueadero.consultarCarrosEnElParqueadero();
		
		// assert
		assertEquals(2,carrosParqueados);
		
	}
	
	@Test
	public void consultarMotosParqueadosTest() {

		// arrange
		int carrosParqueados = 0;
		Moto moto = new Moto(PLACA1,TIPO_MOTO,CILINDRAJE_MOTO);
		repositorioVehiculo.agregar(moto);
		ParqueaderoMoto parqueaderoMoto = new ParqueaderoMoto(moto,new Date());
		ParqueaderoEntity parqueaderoEntity = ParqueaderoBuilder.convertirAEntity(parqueaderoMoto);
		parqueaderoEntity.setVehiculo(repositorioVehiculo.obtenerVehiculoEntityPorPlaca(PLACA1));
		repositorioParqueadero.agregar(parqueaderoEntity);

		// act 
		carrosParqueados = repositorioParqueadero.consultarMotosEnElParqueados();
		
		// assert
		assertEquals(1,carrosParqueados);
		
	}
	
	@Test
	public void registrarSalidaVehiculoTest() {

		// arrange
		Moto moto = new Moto(PLACA1,TIPO_MOTO,CILINDRAJE_MOTO);
		repositorioVehiculo.agregar(moto);
		ParqueaderoMoto parqueaderoMoto = new ParqueaderoMoto(moto,new Date());
		ParqueaderoEntity parqueaderoEntity = ParqueaderoBuilder.convertirAEntity(parqueaderoMoto);
		parqueaderoEntity.setVehiculo(repositorioVehiculo.obtenerVehiculoEntityPorPlaca(PLACA1));
		repositorioParqueadero.agregar(parqueaderoEntity);
		ParqueaderoMoto resultParqueaderoMoto = null;
		Date date = new Date();

		// act 
		repositorioParqueadero.registrarSalidaVehiculo(PLACA1,date);
		resultParqueaderoMoto = (ParqueaderoMoto) repositorioParqueadero.obtenerPorPlaca(PLACA1);
		
		// assert
		assertEquals(PLACA1,resultParqueaderoMoto.getVehiculo().getPlaca());
		assertEquals(date.getTime(),resultParqueaderoMoto.getDateEgreso().getTime());	
	}
	
	
}
