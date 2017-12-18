package persistencia.builder;

import dominio.Parqueadero;
import dominio.ParqueaderoCarro;
import dominio.ParqueaderoMoto;
import persistencia.entidad.ParqueaderoEntity;

public class ParqueaderoBuilder {
	
	private ParqueaderoBuilder() {}
	
	public static Parqueadero convertirADominio(ParqueaderoEntity parqueaderoEntity) {

		return (parqueaderoEntity != null)? convertirADominioPorTipo(parqueaderoEntity):null ;

	}
	
	public static ParqueaderoEntity convertirAEntity(Parqueadero parqueadero) {
		ParqueaderoEntity parqueaderoEntity = new ParqueaderoEntity();
		parqueaderoEntity.setDateIngreso(parqueadero.getDateIngreso());
		parqueaderoEntity.setDateEgreso(parqueadero.getDateEgreso());		
		parqueaderoEntity.setVehiculo(VehiculoBuilder.convertirAEntity(parqueadero.getVehiculo()));
		
		return parqueaderoEntity;
	}
	
	public static Parqueadero convertirADominioPorTipo(ParqueaderoEntity parqueaderoEntity) {
		Parqueadero parqueadero;
		if (parqueaderoEntity.getVehiculo().getTipo() == 1) {
			parqueadero = new ParqueaderoCarro(VehiculoBuilder.convertirADominio(parqueaderoEntity.getVehiculo()),
					parqueaderoEntity.getDateIngreso());
		} else {
			parqueadero = new ParqueaderoMoto(VehiculoBuilder.convertirADominio(parqueaderoEntity.getVehiculo()),
					parqueaderoEntity.getDateIngreso());
		}
		parqueadero
				.setDateEgreso((parqueaderoEntity.getDateEgreso() != null) ? parqueaderoEntity.getDateEgreso() : null);
		return parqueadero;
	}

}
