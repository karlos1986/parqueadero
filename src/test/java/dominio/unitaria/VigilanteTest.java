package dominio.unitaria;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.Test;
import dominio.Vigilante;
import dominio.repositorio.RepositorioVehiculo;

public class VigilanteTest {

	private static final String PLACA_INICIA_CON_A = "AAA000";
	private static final String PLACA_NO_INICIA_CON_A = "BAA000";
	private static final String FECHA_INGRESO_DOMINGO = "2017-12-03 15:03:23";
	private static final String FECHA_INGRESO_LUNES = "2017-12-04 15:03:23";
	private static final String FECHA_INGRESO_MIERCOLES = "2017-12-06 15:03:23";

	@Test
	public void validarPlacaIniciaConA() {

		// arrange
		RepositorioVehiculo repositoriovehiculo = mock(RepositorioVehiculo.class);
		Vigilante vigilante = new Vigilante(repositoriovehiculo);

		// act
		boolean comienzaConA = vigilante.validarCondicionPlaca(PLACA_INICIA_CON_A);

		// assert
		assertTrue(comienzaConA);

	}

	@Test
	public void validarPlacaNoIniciaConA() {

		// arrange
		RepositorioVehiculo repositoriovehiculo = mock(RepositorioVehiculo.class);
		Vigilante vigilante = new Vigilante(repositoriovehiculo);

		// act
		boolean comienzaConA = vigilante.validarCondicionPlaca(PLACA_NO_INICIA_CON_A);

		// assert
		assertFalse(comienzaConA);

	}

	@Test
	public void validarDiaDomingo() {

		// arrange
		RepositorioVehiculo repositoriovehiculo = mock(RepositorioVehiculo.class);
		Vigilante vigilante = new Vigilante(repositoriovehiculo);

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
		Vigilante vigilante = new Vigilante(repositoriovehiculo);

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
		Vigilante vigilante = new Vigilante(repositoriovehiculo);

		// act
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date fechaConHora = simpleDateFormat.parse(FECHA_INGRESO_MIERCOLES);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(fechaConHora);
			boolean diaDomingoLunes = vigilante.validarCondicionDias(calendar);

			// assert
			assertFalse(diaDomingoLunes);

		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}
