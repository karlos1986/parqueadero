package persistencia.builder;

import dominio.Parqueadero;
import dominio.ParqueaderoCarro;
import dominio.ParqueaderoMoto;
import persistencia.entidad.ParqueaderoEntity;
import persistencia.entidad.VehiculoEntity;

public class ParqueaderoBuilder {
	
	public static Parqueadero convertirADominio(ParqueaderoEntity parqueaderoEntity) {

		return (parqueaderoEntity != null)? convertirADominioPorTipo(parqueaderoEntity):null ;

	}
	
	public static ParqueaderoEntity convertirAEntity(Parqueadero Parqueadero) {
		ParqueaderoEntity parqueaderoEntity = new ParqueaderoEntity();
		parqueaderoEntity.setDateIngreso(Parqueadero.getDateIngreso());
		parqueaderoEntity.setDateEgreso(Parqueadero.getDateEgreso());		
		parqueaderoEntity.setVehiculo(VehiculoBuilder.convertirAEntity(Parqueadero.getVehiculo()));
		
		return parqueaderoEntity;
	}
	
	@SuppressWarnings("null")
	public static Parqueadero convertirADominioPorTipo(ParqueaderoEntity parqueaderoEntity) {
		Parqueadero parqueadero = null;
		switch (parqueaderoEntity.getVehiculo().getTipo()) {
			case 1:
				parqueadero = new ParqueaderoCarro(VehiculoBuilder.convertirADominio(parqueaderoEntity.getVehiculo()),
						parqueaderoEntity.getDateIngreso());
				break;
			case 2:
				parqueadero = new ParqueaderoMoto(VehiculoBuilder.convertirADominio(parqueaderoEntity.getVehiculo()),
						parqueaderoEntity.getDateIngreso());
				break;
		}
		parqueadero.setDateEgreso((parqueaderoEntity.getDateEgreso() != null)?parqueaderoEntity.getDateEgreso():null);
		return parqueadero;	
	}

}
