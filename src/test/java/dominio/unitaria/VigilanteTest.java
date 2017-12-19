package dominio.unitaria;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

import dominio.Vigilante;
import dominio.repositorio.RepositorioParqueadero;
import dominio.repositorio.RepositorioVehiculo;

public class VigilanteTest {

	private static final String PLACA_INICIA_CON_A = "AAA000";
	private static final String PLACA_NO_INICIA_CON_A = "BAA000";
	private static final String FECHA_INGRESO_DOMINGO = "2017-12-03 15:03:23";
	private static final String FECHA_INGRESO_LUNES = "2017-12-04 15:03:23";
	private static final String FECHA_INGRESO_MIERCOLES = "2017-12-06 15:03:23";
	private static final double VALOR_DIA = 8000;
	private static final double VALOR_HORA = 1000;
	private static final int TIEMPO_MINUTOS_1 = 4445;// 3 Dias 2 Horas 5 Minutos
	private static final int TIEMPO_MINUTOS_2 = 7859;// 5 Dias 10 Horas 59 Minutos
	private static final int TIEMPO_MINUTOS_3 = 440;// 7 Horas 20 Minutos
	private static final double TOTAL_COBRO_3 = 8000;
	private static final double TOTAL_COBRO_1 = 27000;
	private static final double TOTAL_COBRO_2 = 48000;


	@Test
	public void validarPlacaIniciaConA() {

		// arrange
		RepositorioVehiculo repositoriovehiculo = mock(RepositorioVehiculo.class);
		RepositorioParqueadero repositorioParqueadero = mock(RepositorioParqueadero.class);
		Vigilante vigilante = new Vigilante(repositoriovehiculo,repositorioParqueadero);

		// act
		boolean comienzaConA = vigilante.validarCondicionPlaca(PLACA_INICIA_CON_A);

		// assert
		assertTrue(comienzaConA);

	}

	@Test
	public void validarPlacaNoIniciaConA() {

		// arrange
		RepositorioVehiculo repositoriovehiculo = mock(RepositorioVehiculo.class);
		RepositorioParqueadero repositorioParqueadero = mock(RepositorioParqueadero.class);
		Vigilante vigilante = new Vigilante(repositoriovehiculo,repositorioParqueadero);

		// act
		boolean comienzaConA = vigilante.validarCondicionPlaca(PLACA_NO_INICIA_CON_A);

		// assert
		assertFalse(comienzaConA);

	}

	@Test
	public void validarDiaDomingo() {

		// arrange
		RepositorioVehiculo repositoriovehiculo = mock(RepositorioVehiculo.class);
		RepositorioParqueadero repositorioParqueadero = mock(RepositorioParqueadero.class);
		Vigilante vigilante = new Vigilante(repositoriovehiculo,repositorioParqueadero);
		// act
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date fechaConHora = simpleDateFormat.parse(FECHA_INGRESO_DOMINGO);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(fechaConHora);
			boolean diaDomingoLunes = vigilante.validarCondicionDias(calendar);

			// assert
			assertTrue(diaDomingoLunes);

		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void validarDiaLunes() {

		// arrange
		RepositorioVehiculo repositoriovehiculo = mock(RepositorioVehiculo.class);
		RepositorioParqueadero repositorioParqueadero = mock(RepositorioParqueadero.class);
		Vigilante vigilante = new Vigilante(repositoriovehiculo,repositorioParqueadero);
		
		// act
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date fechaConHora = simpleDateFormat.parse(FECHA_INGRESO_LUNES);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(fechaConHora);
			boolean diaDomingoLunes = vigilante.validarCondicionDias(calendar);

			// assert
			assertTrue(diaDomingoLunes);

		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void validarDiaMiercoles() {

		// arrange
		RepositorioVehiculo repositoriovehiculo = mock(RepositorioVehiculo.class);
		RepositorioParqueadero repositorioParqueadero = mock(RepositorioParqueadero.class);
		Vigilante vigilante = new Vigilante(repositoriovehiculo,repositorioParqueadero);
		
		// act
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date fechaConHora = simpleDateFormat.parse(FECHA_INGRESO_MIERCOLES);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(fechaConHora);
			boolean diaDomingoLunes = vigilante.validarCondicionDias(calendar);
			
			assertTrue(diaDomingoLunes);

		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void calcularCobroTiempoTest1() {

		// arrange
		RepositorioVehiculo repositoriovehiculo = mock(RepositorioVehiculo.class);
		RepositorioParqueadero repositorioParqueadero = mock(RepositorioParqueadero.class);
		Vigilante vigilante = new Vigilante(repositoriovehiculo, repositorioParqueadero);

		// act
		double total = vigilante.calcularCobroTiempo(VALOR_DIA, VALOR_HORA, TIEMPO_MINUTOS_1);

		// assert
		assertEquals(TOTAL_COBRO_1, total, 0.00);

	}
	
	@Test
	public void calcularCobroTiempoTest2() {

		// arrange
		RepositorioVehiculo repositoriovehiculo = mock(RepositorioVehiculo.class);
		RepositorioParqueadero repositorioParqueadero = mock(RepositorioParqueadero.class);
		Vigilante vigilante = new Vigilante(repositoriovehiculo, repositorioParqueadero);

		// act
		double total = vigilante.calcularCobroTiempo(VALOR_DIA, VALOR_HORA, TIEMPO_MINUTOS_2);

		// assert
		assertEquals(TOTAL_COBRO_2, total, 0.00);

	}

	@Test
	public void calcularCobroTiempoTest3() {

		// arrange
		RepositorioVehiculo repositoriovehiculo = mock(RepositorioVehiculo.class);
		RepositorioParqueadero repositorioParqueadero = mock(RepositorioParqueadero.class);
		Vigilante vigilante = new Vigilante(repositoriovehiculo, repositorioParqueadero);

		// act
		double total = vigilante.calcularCobroTiempo(VALOR_DIA, VALOR_HORA, TIEMPO_MINUTOS_3);

		// assert
		assertEquals(TOTAL_COBRO_3, total, 0.00);

	}
}

