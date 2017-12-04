package dominio.unitaria;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import dominio.ParqueaderoMoto;

public class ParqueaderoMotoTest {

	private static final int CELDAS = 10;
	private static final double VALOR_HORA = 500;
	private static final double VALOR_DIA = 4000;
	private static final int CORTE_COBRO_POR_HORAS = 9;
	private static final int CORTE_COBRO_POR_CILINDRAJE = 500;
	private static final double VALOR_POR_CILINDRAJE = 2000;
	

	@Test
	public void crearParqueaderoMotoTest() {
		
		// arrange
		ParqueaderoMoto parqueaderoMoto = new ParqueaderoMoto();
		
		// assert
						
		assertEquals(CELDAS, parqueaderoMoto.getCeldas());
		assertEquals(CORTE_COBRO_POR_HORAS, parqueaderoMoto.getcorteCobroPorHoras());
		assertEquals(VALOR_HORA,parqueaderoMoto.getValorHora(),0.00);
		assertEquals(VALOR_DIA, parqueaderoMoto.getValorDia(),0.00);
		assertEquals(VALOR_POR_CILINDRAJE, parqueaderoMoto.getCobroPorCilindraje(),0.00);
		assertEquals(CORTE_COBRO_POR_CILINDRAJE, parqueaderoMoto.getCorteCobroPorCilindraje());
		
	}

}
