package dominio.unitaria;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import dominio.ParqueaderoCarro;


public class ParqueaderoCarroTest {

	private static final int CELDAS = 20;
	private static final double VALOR_HORA = 1000;
	private static final double VALOR_DIA = 8000;
	private static final int CORTE_COBRO_POR_HORAS = 9;
	

	@Test
	public void crearParqueaderoCarroTest() {
		
		// arrange
		ParqueaderoCarro parqueaderoCarro = new ParqueaderoCarro();
		
		// assert
						
		assertEquals(CELDAS, parqueaderoCarro.getCeldas());
		assertEquals(CORTE_COBRO_POR_HORAS, parqueaderoCarro.getcorteCobroPorHoras());
		assertEquals(VALOR_HORA,parqueaderoCarro.getValorHora(),0.00);
		assertEquals(VALOR_DIA, parqueaderoCarro.getValorDia(),0.00);
		
	}

}
