package dominio.unitaria;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import org.junit.Test;
import dominio.Vehiculo;
import repuestas.Recibo;

public class ReciboTest {

		private static final double VALOR_COBRO = 0.00;
		
		@Test
		public void crearReciboTest() {
			
			// arrange
			Vehiculo vehiculo = mock(Vehiculo.class);
			
			// act
			Recibo recibo = new Recibo(vehiculo, VALOR_COBRO);

			// assert
			assertEquals(vehiculo, recibo.getVehiculo());
			assertEquals(VALOR_COBRO, recibo.getValorCobro(),0.00);
			
	}

}
