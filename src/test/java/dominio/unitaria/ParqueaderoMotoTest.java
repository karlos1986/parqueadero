package dominio.unitaria;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;

import java.util.Date;

import org.junit.Test;

import dominio.Moto;
import dominio.ParqueaderoMoto;

public class ParqueaderoMotoTest {

	private static final int CELDAS = 10;
	private static final double VALOR_HORA = 500;
	private static final double VALOR_DIA = 4000;
	private static final int CORTE_COBRO_POR_HORAS = 9;
	private static final int CORTE_COBRO_POR_CILINDRAJE = 500;
	private static final double VALOR_POR_CILINDRAJE = 2000;
	private static final Date FECHA_ACTUAL = new Date();
	private static final Moto VEHICULO = mock(Moto.class);
	

	@SuppressWarnings("deprecation")
	@Test
	public void crearParqueaderoMotoTest() {
		
		// arrange
		ParqueaderoMoto parqueaderoMoto = new ParqueaderoMoto(VEHICULO,FECHA_ACTUAL);
		
		// assert
						
		assertEquals(CELDAS, parqueaderoMoto.getCeldas());
		assertEquals(CORTE_COBRO_POR_HORAS, parqueaderoMoto.getcorteCobroPorHoras());
		assertEquals(VALOR_HORA,parqueaderoMoto.getValorHora(),0.00);
		assertEquals(VALOR_DIA, parqueaderoMoto.getValorDia(),0.00);
		assertEquals(VALOR_POR_CILINDRAJE, parqueaderoMoto.getCobroPorCilindraje(),0.00);
		assertEquals(CORTE_COBRO_POR_CILINDRAJE, parqueaderoMoto.getCorteCobroPorCilindraje());
		assertEquals(FECHA_ACTUAL.getMinutes(),parqueaderoMoto.getDateIngreso().getMinutes());
		assertEquals(FECHA_ACTUAL.getHours(),parqueaderoMoto.getDateIngreso().getHours());
		assertEquals(FECHA_ACTUAL.getDay(),parqueaderoMoto.getDateIngreso().getDay());
		assertEquals(FECHA_ACTUAL.getMonth(),parqueaderoMoto.getDateIngreso().getMonth());
		assertEquals(FECHA_ACTUAL.getYear(),parqueaderoMoto.getDateIngreso().getYear());
		assertEquals(VEHICULO, parqueaderoMoto.getVehiculo());
		assertNull(parqueaderoMoto.getDateEgreso());
	}

}
