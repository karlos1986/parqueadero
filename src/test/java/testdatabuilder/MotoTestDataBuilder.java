package testdatabuilder;

import dominio.Moto;

public class MotoTestDataBuilder {

	private static final int TIPO = 2;
	private static final String PLACA = "AAA000";
	private static final int CILINDRAJE = 200;
	
	private String placa;
	private int tipo;
	private int cilindraje;

	public MotoTestDataBuilder() {
		this.conPlaca(PLACA);
		this.conTipo(TIPO);
		this.conCilindraje(CILINDRAJE);
	}

	public MotoTestDataBuilder conPlaca(String placa) {
		this.placa = placa;
		return this;
	}

	public MotoTestDataBuilder conTipo(int tipo) {
		this.tipo = tipo;
		return this;
	}
	
	public MotoTestDataBuilder conCilindraje(int cilindraje) {
		this.cilindraje = cilindraje;
		return this;
		
	}
		
	public Moto build() {
		return new Moto(this.placa, this.tipo,this.cilindraje);
	}


}
