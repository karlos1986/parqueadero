package testdatabuilder;

import dominio.Carro;

public class CarroTestDataBuilder {
	
	private static final int TIPO = 1;
	private static final String PLACA = "AAA000";
	
	private String placa;
	private int tipo;

	public CarroTestDataBuilder() {
		this.conPlaca(PLACA);
		this.conTipo(TIPO);
	}

	public CarroTestDataBuilder conPlaca(String placa) {
		this.placa = placa;
		return this;
	}

	
	public CarroTestDataBuilder conTipo(int tipo) {
		this.tipo = tipo;
		return this;
	}
		
	public Carro build() {
		return new Carro(this.placa, this.tipo);
	}
}
