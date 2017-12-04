package dominio;

import java.util.Calendar;

import dominio.repositorio.RepositorioVehiculo;

public class Vigilante {
	
	public static final String El_VEHICULO_NO_ESTA_AUTORIZADO = "El vehiculo no esta autorizado a ingresar.";

	private RepositorioVehiculo repositorioVehiculo;

	public Vigilante(RepositorioVehiculo repositorioVehiculo) {
		this.repositorioVehiculo = repositorioVehiculo;
	}
	
	public boolean registrarVehiculo(Vehiculo vehiculo) {
		Calendar calendar = Calendar.getInstance(); 
		if(validarCondicionDias(calendar) && validarCondicionPlaca(vehiculo.getPlaca()) && validarDisponibilidadCeldas()) {
			return false;
		}
		return true;
	}
	
	private boolean validarDisponibilidadCeldas() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean validarCondicionPlaca(String Placa) {
		return (Placa.charAt(0) == 'A')?true:false;
	}

	public boolean validarCondicionDias(Calendar calendar) {
		switch (calendar.get(Calendar.DAY_OF_WEEK)){
			case 1:
			case 2:
				return true;
			default:
				return false;
		}
		
	}

	public boolean salidaVehiculo(Vehiculo vehiculo) {
		return false;
		
	}
	
	public boolean consultarVehiculos() {
		return false;
		
	}

	

}
