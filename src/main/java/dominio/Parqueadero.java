package dominio;

import java.util.Date;

public abstract class Parqueadero {

	protected int celdas;
	protected double valorHora;
	protected double valorDia;
	protected int corteCobroPorHoras;
	protected Date dateIngreso;
	protected Date dateEgreso;
	protected Vehiculo vehiculo;


	
	public Parqueadero() {

	}

	public double getValorHora() {
		return valorHora;
	}

	public double getValorDia() {
		return valorDia;
	}

	public int getCeldas() {
		return celdas;
	}

	public int getcorteCobroPorHoras() {
		return corteCobroPorHoras;
	}
	
	public Date getDateIngreso() {
		return dateIngreso;
	}

	public Date getDateEgreso() {
		return dateEgreso;
	}
	
	public void setDateEgreso(Date dateEgreso) {
		this.dateEgreso = dateEgreso;
	}
	
	public Vehiculo getVehiculo() {
		return vehiculo;
	}
}
