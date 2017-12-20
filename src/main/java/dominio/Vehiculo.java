package dominio;

import org.springframework.stereotype.Component;

@Component
public abstract class Vehiculo {
	
	protected String placa;
	protected int tipo;// 1 -> carro , 2 -> moto
	
	public Vehiculo() {

	}

	public String getPlaca() {
		return placa;
	}

	public int getTipo() {
		return tipo;
	}

}
