package dominio;

public class Parqueadero {

	protected int celdas;
	protected double valorHora;
	protected double valorDia;
	
	public Parqueadero() {
		// TODO Auto-generated constructor stub
	}

	public double getValorHora() {
		return valorHora;
	}

	public void setValorHora(double valorHora) {
		this.valorHora = valorHora;
	}

	public double getValorDia() {
		return valorDia;
	}

	public void setValorDia(double valorDia) {
		this.valorDia = valorDia;
	}

	public int getCeldas() {
		return celdas;
	}

	public void setCeldas(int celdas) {
		this.celdas = celdas;
	}
}
