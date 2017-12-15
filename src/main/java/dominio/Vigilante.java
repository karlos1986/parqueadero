package dominio;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import dominio.excepcion.IngresarExcepcion;
import dominio.repositorio.RepositorioParqueadero;
import dominio.repositorio.RepositorioVehiculo;
import persistencia.builder.ParqueaderoBuilder;
import persistencia.entidad.ParqueaderoEntity;

public class Vigilante {
	
	public static final String El_VEHICULO_NO_ESTA_AUTORIZADO = "El vehiculo no esta autorizado a ingresar";
	public static final String NO_HAY_CELDAS_DISPONIBLES = "No Hay Celdas Disponibles";
	
	private RepositorioVehiculo repositorioVehiculo;
	private RepositorioParqueadero repositorioParqueadero;
	

	public Vigilante(RepositorioVehiculo repositorioVehiculo,RepositorioParqueadero repositorioParqueadero) {
		this.repositorioVehiculo = repositorioVehiculo;
		this.repositorioParqueadero = repositorioParqueadero;
	}
	
	public boolean registrarVehiculo(Vehiculo vehiculo) {
		Calendar calendar = Calendar.getInstance(); 
		if(validarCondicionDias(calendar) && validarCondicionPlaca(vehiculo.getPlaca())) {
			throw new IngresarExcepcion(El_VEHICULO_NO_ESTA_AUTORIZADO);
		}
		if(vehiculo.getTipo() == 1 && validarCeldaDisponibleCarro()) {
			parquearCarro(vehiculo);
			return true;
		}
		if(vehiculo.getTipo() == 2 && validarCeldaDisponibleMoto()){
			parquearMoto(vehiculo);
			return true;
		}
		throw new IngresarExcepcion(NO_HAY_CELDAS_DISPONIBLES);
	}
	
	public boolean validarCeldaDisponibleMoto() {
		return (repositorioParqueadero.consultarMotosEnElParqueados() < 10)?true:false;
	}

	public boolean validarCeldaDisponibleCarro() {
		return (repositorioParqueadero.consultarCarrosEnElParqueadero() < 20)?true:false;
	}

	public void parquearMoto(Vehiculo vehiculo) {
		Moto moto = (Moto) vehiculo;
		repositorioVehiculo.agregar(moto);
		ParqueaderoMoto parqueaderoMoto = new ParqueaderoMoto(moto,new Date());
		ParqueaderoEntity parqueaderoEntity = ParqueaderoBuilder.convertirAEntity(parqueaderoMoto);
		parqueaderoEntity.setVehiculo(repositorioVehiculo.obtenerVehiculoEntityPorPlaca(moto.getPlaca()));
		repositorioParqueadero.agregar(parqueaderoEntity);	
	}

	public void parquearCarro(Vehiculo vehiculo) {
		Carro carro = (Carro) vehiculo;
		repositorioVehiculo.agregar(carro);
		ParqueaderoCarro parqueaderoCarro = new ParqueaderoCarro(carro,new Date());
		ParqueaderoEntity parqueaderoEntity = ParqueaderoBuilder.convertirAEntity(parqueaderoCarro);
		parqueaderoEntity.setVehiculo(repositorioVehiculo.obtenerVehiculoEntityPorPlaca(carro.getPlaca()));
		repositorioParqueadero.agregar(parqueaderoEntity);	
	}

	public boolean validarCondicionPlaca(String Placa) {
		return (Placa.charAt(0) == 'A')?true:false;
	}

	public boolean validarCondicionDias(Calendar calendar) {
		if (calendar.get(Calendar.DAY_OF_WEEK) != 1 || calendar.get(Calendar.DAY_OF_WEEK) != 2){//1 -> Domingo , 2 -> Lunes
				return true;		
		}
		return false;
	}

	public double salidaVehiculo(Vehiculo vehiculo) {	
		double valorFinal = 0;
		if(vehiculo.getTipo() == 1) {
			Carro carro = (Carro) vehiculo;
			ParqueaderoCarro parqueaderoCarro = (ParqueaderoCarro)
					ParqueaderoBuilder.convertirADominio(repositorioParqueadero.obtenerParqueaderoEntity(carro.getPlaca()));
			if (parqueaderoCarro != null) {
				parqueaderoCarro.setDateEgreso(new Date());
				repositorioParqueadero.registrarSalidaVehiculo(vehiculo.getPlaca(),new Date());
				valorFinal = calcularCobroTiempoCarro(parqueaderoCarro);
				return  valorFinal;
			}		
		}if(vehiculo.getTipo() == 2) {
			Moto moto = (Moto) vehiculo;
			ParqueaderoMoto parqueaderoMoto = (ParqueaderoMoto)
					ParqueaderoBuilder.convertirADominio(repositorioParqueadero.obtenerParqueaderoEntity(vehiculo.getPlaca()));
			if (parqueaderoMoto != null) {
				parqueaderoMoto.setDateEgreso(new Date());
				repositorioParqueadero.registrarSalidaVehiculo(vehiculo.getPlaca(),new Date());
				valorFinal = calcularCobroTiempoMoto(parqueaderoMoto) + cobroCilindraje(moto,parqueaderoMoto);
				return valorFinal;
			}
		}
		return 0; //no se encontro registro 
	}
	
	private double cobroCilindraje(Moto moto,ParqueaderoMoto parqueaderoMoto) {	
		return (moto.getCilindraje()  >  parqueaderoMoto.getCorteCobroPorCilindraje())? parqueaderoMoto.getCobroPorCilindraje():0;
	}

	public double calcularCobroTiempoMoto(ParqueaderoMoto parqueaderoMoto) {
		int diferenciaTiempo = (int) (parqueaderoMoto.getDateEgreso().getTime() - parqueaderoMoto.getDateIngreso().getTime())/60000;
		return (calcularCobroTiempo(parqueaderoMoto.getValorDia(),parqueaderoMoto.getValorHora(),diferenciaTiempo));
	}

	public double calcularCobroTiempoCarro(ParqueaderoCarro parqueaderoCarro) {
		int diferenciaTiempo = (int) (parqueaderoCarro.getDateEgreso().getTime() - parqueaderoCarro.getDateIngreso().getTime())/60000;
		return (calcularCobroTiempo(parqueaderoCarro.getValorDia(),parqueaderoCarro.getValorHora(),diferenciaTiempo));
	}

	public double calcularCobroTiempo(Double valorDia,Double valorHora,int tiempo) {
		double valorTotal = 0;
		valorTotal += valorDia* (tiempo / 1440);
		tiempo =  (tiempo % 1440);
		if((tiempo >= 540)) {
			return valorTotal += valorDia;
		}else {
			valorTotal += valorHora*(tiempo / 60);
			tiempo =  (tiempo % 60);
			return (tiempo > 0)?valorTotal += valorHora:valorTotal;
		}
	}

	public List<Vehiculo> consultarVehiculos() {
		List<Vehiculo> vehiculos = repositorioVehiculo.consultarVehiculos();
        return vehiculos;
	}
	
	public List<Parqueadero> consultarParqueadero() {
		List<Parqueadero> parqueadero = repositorioParqueadero.consultarParqueadero();
        return parqueadero;
	}

}
