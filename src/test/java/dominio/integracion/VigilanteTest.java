package dominio.integracion;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dominio.Carro;
import dominio.Moto;
import dominio.ParqueaderoCarro;
import dominio.ParqueaderoMoto;
import dominio.Vigilante;
import dominio.repositorio.RepositorioParqueadero;
import dominio.repositorio.RepositorioVehiculo;
import persistencia.builder.ParqueaderoBuilder;
import persistencia.entidad.ParqueaderoEntity;
import persistencia.sistema.SistemaDePersistencia;

public class VigilanteTest {
	
	private static final String PLACA = "AAA001";
	private static final String PLACA1 = "BBB001";
	private static final String PLACA2 = "BBB002";
	private static final int TIPO_CARRO = 1;
	private static final int TIPO_MOTO = 2;
	private static final int CILINDRAJE_MOTO = 600;
	private static final int CILINDRAJE_MOTO_MENOR = 400;
	private static final Date FECHA_ACTUAL = new Date();
	private static final double VALOR_TOTAL = 0;
	private static final double VALOR_TOTAL_MOTO_600CC = 2000;


	
	private RepositorioVehiculo repositorioVehiculo;
	private SistemaDePersistencia sistemaPersistencia;
	private RepositorioParqueadero repositorioParqueadero;
	
	@Before
	public void setUp() {
		
		sistemaPersistencia = SistemaDePersistencia.getInstance();
		repositorioVehiculo = sistemaPersistencia.obtenerRepositorioVehiculo();
		repositorioParqueadero = sistemaPersistencia.obtenerRepositorioParqueadero();
	
	}
	
	@After
	public void setDown() {		
		repositorioParqueadero.borrarContenidoTablaParqueadero();
		repositorioVehiculo.borrarContenidoTablaVehiculo();
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void parquearCarro(){
		
		// arrange
		Carro carro = new Carro(PLACA,TIPO_CARRO);
		Vigilante vigilante = new Vigilante(repositorioVehiculo,repositorioParqueadero);
		ParqueaderoCarro resultParqueaderoCarro = null;
		
		
		// act 
		vigilante.registrarVehiculo(carro);
		resultParqueaderoCarro = (ParqueaderoCarro) repositorioParqueadero.obtenerPorPlaca(carro.getPlaca()) ;
		
		// assert
		assertEquals(PLACA, resultParqueaderoCarro.getVehiculo().getPlaca());
		assertEquals(TIPO_CARRO,  resultParqueaderoCarro.getVehiculo().getTipo());
		assertEquals(FECHA_ACTUAL.getMinutes(),resultParqueaderoCarro.getDateIngreso().getMinutes());
		assertEquals(FECHA_ACTUAL.getHours(),resultParqueaderoCarro.getDateIngreso().getHours());
		assertEquals(FECHA_ACTUAL.getDay(),resultParqueaderoCarro.getDateIngreso().getDay());
		assertEquals(FECHA_ACTUAL.getMonth(),resultParqueaderoCarro.getDateIngreso().getMonth());
		assertEquals(FECHA_ACTUAL.getYear(),resultParqueaderoCarro.getDateIngreso().getYear());
		assertNull(resultParqueaderoCarro.getDateEgreso());

		
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void parquearMoto(){
		
		// arrange
		Moto moto = new Moto(PLACA,TIPO_MOTO,CILINDRAJE_MOTO);
		Vigilante vigilante = new Vigilante(repositorioVehiculo,repositorioParqueadero);
		ParqueaderoMoto resultParqueaderoMoto = null;
	
		// act 
		vigilante.registrarVehiculo(moto);
		resultParqueaderoMoto = (ParqueaderoMoto) repositorioParqueadero.obtenerPorPlaca(moto.getPlaca()) ;
		
		// assert
		assertEquals(PLACA, resultParqueaderoMoto.getVehiculo().getPlaca());
		assertEquals(TIPO_MOTO,  resultParqueaderoMoto.getVehiculo().getTipo());
		assertEquals(FECHA_ACTUAL.getMinutes(), resultParqueaderoMoto.getDateIngreso().getMinutes());
		assertEquals(FECHA_ACTUAL.getHours(), resultParqueaderoMoto.getDateIngreso().getHours());
		assertEquals(FECHA_ACTUAL.getDay(), resultParqueaderoMoto.getDateIngreso().getDay());
		assertEquals(FECHA_ACTUAL.getMonth(), resultParqueaderoMoto.getDateIngreso().getMonth());
		assertEquals(FECHA_ACTUAL.getYear(), resultParqueaderoMoto.getDateIngreso().getYear());
		assertNull(resultParqueaderoMoto.getDateEgreso());

		
	}
	
	@Test
	public void salidaVehiculoCarroTest(){
		
		// arrange
		Carro carro = new Carro(PLACA,TIPO_CARRO);
		Vigilante vigilante = new Vigilante(repositorioVehiculo,repositorioParqueadero);
		vigilante.parquearCarro(carro);
		
		// act 
		double valorTotal = vigilante.salidaVehiculo(carro);
		
		// assert
		assertEquals(VALOR_TOTAL,valorTotal,0.00);	
	}
	
	@Test
	public void salidaVehiculoMotoTest(){
		
		// arrange
		Moto moto = new Moto(PLACA,TIPO_MOTO,CILINDRAJE_MOTO);
		Vigilante vigilante = new Vigilante(repositorioVehiculo,repositorioParqueadero);
		vigilante.parquearMoto(moto);
		
		// act 
		double valorTotal = vigilante.salidaVehiculo(moto);
		
		// assert
		assertEquals(VALOR_TOTAL_MOTO_600CC,valorTotal,0.00);	
	}
	
	@Test
	public void salidaVehiculoMotoTest1(){
		
		// arrange
		Moto moto = new Moto(PLACA,TIPO_MOTO,CILINDRAJE_MOTO_MENOR);
		Vigilante vigilante = new Vigilante(repositorioVehiculo,repositorioParqueadero);
		vigilante.parquearMoto(moto);
		
		// act 
		double valorTotal = vigilante.salidaVehiculo(moto);
		
		// assert
		assertEquals(VALOR_TOTAL,valorTotal,0.00);	
	}
	
	
}
