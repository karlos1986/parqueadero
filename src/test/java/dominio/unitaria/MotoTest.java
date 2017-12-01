package dominio.unitaria;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import dominio.Moto;
import testdatabuilder.MotoTestDataBuilder;

public class MotoTest {

	private static final String PLACA = "AAA000";
	private static final int TIPO = 2;
	private static final int CILINDRAJE = 200;

	
	@Test
	public void crearMotoTest() {
		
		// arrange
		MotoTestDataBuilder motoTestDataBuilder = new MotoTestDataBuilder().
				conPlaca(PLACA).
				conTipo(TIPO).
				conCilindraje(CILINDRAJE);
				
		// act
		Moto moto = motoTestDataBuilder.build();

		// assert
		assertEquals(PLACA, moto.getPlaca());
		assertEquals(TIPO, moto.getTipo());
		assertEquals(CILINDRAJE,moto.getCilindraje());
		
	}
}
