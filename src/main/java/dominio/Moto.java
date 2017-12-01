package dominio;

public class Moto extends Vehiculo  {
	
	private int cilindraje;

	public Moto(String placa,int tipo,int cilindraje) {
		super();
		this.placa = placa;
		this.tipo = tipo;
		this.setCilindraje(cilindraje);
	}

	public int getCilindraje() {
		return cilindraje;
	}

	public void setCilindraje(int cilindraje) {
		this.cilindraje = cilindraje;
	}

}
