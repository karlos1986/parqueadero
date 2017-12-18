package repuestas;

import org.springframework.stereotype.Component;

import dominio.Vehiculo;

@Component
public class Recibo {
	
	private Vehiculo vehiculo;
	private String mensaje;
		

	public Recibo(Vehiculo vehiculo ,String mensaje) {
		this.vehiculo = vehiculo;
		this.mensaje = mensaje;
	}

}
