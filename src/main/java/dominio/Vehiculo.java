package dominio;

public class Vehiculo {
	
	protected String placa;
	protected int tipo;// 1 -> carro , 2 -> moto
	
	public Vehiculo() {
		// TODO Auto-generated constructor stub
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}
	
	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}
	
}
