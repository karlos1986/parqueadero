package dominio;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import dominio.excepcion.CalcularCobroExcepcion;
import dominio.excepcion.IngresarExcepcion;
import dominio.repositorio.RepositorioParqueadero;
import dominio.repositorio.RepositorioVehiculo;
import persistencia.builder.ParqueaderoBuilder;
import persistencia.entidad.ParqueaderoEntity;
import repuestas.Recibo;

public class Vigilante {
	
	public static final String EL_VEHICULO_NO_ESTA_AUTORIZADO = "El vehiculo no esta autorizado a ingresar";
	public static final String NO_HAY_CELDAS_DISPONIBLES = "No Hay Celdas Disponibles";
	public static final String NO_SE_ENCONTRO_REGISTRO = "No se encontro registro";
	public static final String ENCONTRO_REGISTRO_DE_VEHICULO = "El vehiculo se encuentra registrado en el parqueadero";
	
	
	private RepositorioVehiculo repositorioVehiculo;
	private RepositorioParqueadero repositorioParqueadero;
	

	public Vigilante(RepositorioVehiculo repositorioVehiculo,RepositorioParqueadero repositorioParqueadero) {
		this.repositorioVehiculo = repositorioVehiculo;
		this.repositorioParqueadero = repositorioParqueadero;
	}
	
	public void registrarVehiculo(Vehiculo vehiculo) {
		validarCondicionesRegistro(vehiculo.getPlaca());
		if (vehiculo.getTipo() == 1 && validarCeldaDisponibleCarro()) {
			parquearCarro(vehiculo);
		} else if (vehiculo.getTipo() == 2 && validarCeldaDisponibleMoto()) {
			parquearMoto(vehiculo);
		} else {
			throw new IngresarExcepcion(NO_HAY_CELDAS_DISPONIBLES);
		}

	}

	private void validarCondicionesRegistro(String placa) {
		Calendar calendar = Calendar.getInstance();
		if (validarCondicionDias(calendar) && validarCondicionPlaca(placa)) {
			throw new IngresarExcepcion(EL_VEHICULO_NO_ESTA_AUTORIZADO);
		}
		if(repositorioParqueadero.obtenerCountParqueaderoEntity(placa) > 0) {
			throw new IngresarExcepcion(ENCONTRO_REGISTRO_DE_VEHICULO);
		}
	}

	public boolean validarCeldaDisponibleMoto() {
		return (repositorioParqueadero.consultarMotosEnElParqueados() < 10);
	}

	public boolean validarCeldaDisponibleCarro() {
		return (repositorioParqueadero.consultarCarrosEnElParqueadero() < 20);
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

	public boolean validarCondicionPlaca(String placa) {
		return (placa.charAt(0) == 'A');
	}

	public boolean validarCondicionDias(Calendar calendar) {
		return (calendar.get(Calendar.DAY_OF_WEEK) != 1 || calendar.get(Calendar.DAY_OF_WEEK) != 2);//1 -> Domingo , 2 -> Lunes
	}

	public Recibo salidaVehiculo(String placa) {
		Vehiculo vehiculo = repositorioVehiculo.obtenerPorPlaca(placa);
		if (vehiculo != null) {
			if (vehiculo.getTipo() == 1) {
				return salidaCarro(vehiculo);
			}else {
				return salidaMoto(vehiculo);
			}
		}
		throw new CalcularCobroExcepcion(NO_SE_ENCONTRO_REGISTRO);
	}

	private Recibo salidaMoto(Vehiculo vehiculo) {
		double valorFinal = 0;
		Moto moto = (Moto) vehiculo;
		ParqueaderoMoto parqueaderoMoto = (ParqueaderoMoto) ParqueaderoBuilder
				.convertirADominio(repositorioParqueadero.obtenerParqueaderoEntity(vehiculo.getPlaca()));
		parqueaderoMoto.setDateEgreso(new Date());
		repositorioParqueadero.registrarSalidaVehiculo(vehiculo.getPlaca(), new Date());
		valorFinal = calcularCobroTiempoMoto(parqueaderoMoto) + cobroCilindraje(moto, parqueaderoMoto);
		return new Recibo(moto,valorFinal);
	}

	private Recibo salidaCarro(Vehiculo vehiculo) {
		double valorFinal = 0;
		Carro carro = (Carro) vehiculo;
		ParqueaderoCarro parqueaderoCarro = (ParqueaderoCarro) ParqueaderoBuilder
				.convertirADominio(repositorioParqueadero.obtenerParqueaderoEntity(carro.getPlaca()));
		parqueaderoCarro.setDateEgreso(new Date());
		repositorioParqueadero.registrarSalidaVehiculo(vehiculo.getPlaca(), new Date());
		valorFinal = calcularCobroTiempoCarro(parqueaderoCarro);
		return new Recibo(carro, valorFinal);

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
		valorTotal += valorDia* (int)(tiempo / 1440f);
		tiempo =  (tiempo % 1440);
		if((tiempo >= 540)) {
			valorTotal += valorDia;
			return valorTotal;
		}else {
			valorTotal += valorHora*(int)(tiempo / 60f);
			tiempo =  (tiempo % 60);
			if (tiempo > 0) {
				valorTotal += valorHora;
			}
			return valorTotal;
		}
	}

	public List<Vehiculo> consultarVehiculos() {
        return repositorioVehiculo.consultarVehiculos();
	}
	
	public List<Parqueadero> consultarParqueadero() {
        return repositorioParqueadero.consultarParqueadero();
	}

}
