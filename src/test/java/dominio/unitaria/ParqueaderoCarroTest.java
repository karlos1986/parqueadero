package dominio.unitaria;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;

import java.util.Date;

import org.junit.Test;

import dominio.Carro;
import dominio.ParqueaderoCarro;


public class ParqueaderoCarroTest {

	private static final int CELDAS = 20;
	private static final double VALOR_HORA = 1000;
	private static final double VALOR_DIA = 8000;
	private static final int CORTE_COBRO_POR_HORAS = 9;
	private static final Date FECHA_ACTUAL = new Date();
	private static final Carro VEHICULO = mock(Carro.class);
	



	@SuppressWarnings("deprecation")
	@Test
	public void crearParqueaderoCarroTest() {
		
		// arrange
		ParqueaderoCarro parqueaderoCarro = new ParqueaderoCarro(VEHICULO,FECHA_ACTUAL);
		
		// assert
						
		assertEquals(CELDAS, parqueaderoCarro.getCeldas());
		assertEquals(CORTE_COBRO_POR_HORAS, parqueaderoCarro.getcorteCobroPorHoras());
		assertEquals(VALOR_HORA,parqueaderoCarro.getValorHora(),0.00);
		assertEquals(VALOR_DIA, parqueaderoCarro.getValorDia(),0.00);
		assertEquals(FECHA_ACTUAL.getMinutes(),parqueaderoCarro.getDateIngreso().getMinutes());
		assertEquals(FECHA_ACTUAL.getHours(),parqueaderoCarro.getDateIngreso().getHours());
		assertEquals(FECHA_ACTUAL.getDay(),parqueaderoCarro.getDateIngreso().getDay());
		assertEquals(FECHA_ACTUAL.getMonth(),parqueaderoCarro.getDateIngreso().getMonth());
		assertEquals(FECHA_ACTUAL.getYear(),parqueaderoCarro.getDateIngreso().getYear());
		assertEquals(VEHICULO, parqueaderoCarro.getVehiculo());
		assertNull(parqueaderoCarro.getDateEgreso());
		
		
		
	}

}
