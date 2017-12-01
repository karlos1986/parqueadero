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
			vehiculoEntity.setCilindraje((vehiculo.getTipo()==1)?null:null);
			return vehiculoEntity;
		}
		
		public static Vehiculo convertirADominioPorTipo(VehiculoEntity vehiculoEntity) {
			Vehiculo vehiculo = null;
			switch (vehiculoEntity.getTipo()) {
				case 1:
					vehiculo = new Carro(vehiculoEntity.getPlaca(),vehiculoEntity.getTipo());
					break;
				case 2:
					vehiculo = new Moto(vehiculoEntity.getPlaca(),vehiculoEntity.getTipo(),vehiculoEntity.getCilindraje());
					break;
			}
			return vehiculo;	
		}

}
