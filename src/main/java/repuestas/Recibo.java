package repuestas;

import org.springframework.stereotype.Component;

import dominio.Vehiculo;

@Component
public class Recibo {
	
	private Vehiculo vehiculo;
	private double valorCobro;
	
	public double getValorCobro() {
		return valorCobro;
	}


	public Vehiculo getVehiculo() {
		return vehiculo;
	}


	public Recibo(Vehiculo vehiculo ,double valorCobro) {
		this.vehiculo = vehiculo;
		this.valorCobro = valorCobro;
	}

}
