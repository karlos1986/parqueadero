package persistencia.builder;

import dominio.Carro;
import dominio.Moto;
import dominio.Vehiculo;
import persistencia.entidad.VehiculoEntity;


public class VehiculoBuilder {
	
	private VehiculoBuilder() {}
		
		public static Vehiculo convertirADominio(VehiculoEntity vehiculoEntity) {

			return (vehiculoEntity != null)? convertirADominioPorTipo(vehiculoEntity):null ;

		}
		
		public static VehiculoEntity convertirAEntity(Vehiculo vehiculo) {
			VehiculoEntity vehiculoEntity = new VehiculoEntity();
			vehiculoEntity.setPlaca(vehiculo.getPlaca());
			vehiculoEntity.setTipo(vehiculo.getTipo());
			if(vehiculo.getTipo()==2) {
				Moto moto = (Moto) vehiculo ;
				vehiculoEntity.setCilindraje(moto.getCilindraje());
			}
			return vehiculoEntity;
		}
		
	public static Vehiculo convertirADominioPorTipo(VehiculoEntity vehiculoEntity) {
		Vehiculo vehiculo = null;
		if (vehiculoEntity.getTipo() == 1) {

			vehiculo = new Carro(vehiculoEntity.getPlaca(), vehiculoEntity.getTipo());

		} else {

			vehiculo = new Moto(vehiculoEntity.getPlaca(), vehiculoEntity.getTipo(), vehiculoEntity.getCilindraje());

		}
		return vehiculo;
	}

}
