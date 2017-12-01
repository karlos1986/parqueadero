package dominio.unitaria;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import dominio.Carro;
import testdatabuilder.CarroTestDataBuilder;

public class CarroTest {
	
	private static final String PLACA = "AAA000";
	private static final int TIPO = 1;
	
	@Test
	public void crearCarroTest() {
		
		// arrange
		CarroTestDataBuilder carroTestDataBuilder = new CarroTestDataBuilder().
				conPlaca(PLACA).
				conTipo(TIPO);
				
		// act
		Carro carro = carroTestDataBuilder.build();

		// assert
		assertEquals(PLACA, carro.getPlaca());
		assertEquals(TIPO, carro.getTipo());
		
	}

}
