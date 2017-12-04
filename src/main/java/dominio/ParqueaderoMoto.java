package dominio;

public class ParqueaderoMoto extends Parqueadero {
	
	private static final int CELDAS = 10;
	private static final double VALOR_HORA = 500;
	private static final double VALOR_DIA = 4000;
	private static final int CORTE_COBRO_POR_HORAS = 9;
	private static final int CORTE_COBRO_POR_CILINDRAJE = 500;
	private static final double VALOR_POR_CILINDRAJE = 2000;
	
	private double cobroPorCilindraje;
	private int corteCobroPorCilindraje;
	

	public ParqueaderoMoto () {
		super();
		this.celdas=CELDAS;
		this.valorDia=VALOR_DIA;
		this.valorHora=VALOR_HORA;
		this.corteCobroPorHoras = CORTE_COBRO_POR_HORAS;
		this.cobroPorCilindraje = VALOR_POR_CILINDRAJE;
		this.corteCobroPorCilindraje = CORTE_COBRO_POR_CILINDRAJE;
	
	}


	public double getCobroPorCilindraje() {
		return cobroPorCilindraje;
	}

	public int getCorteCobroPorCilindraje() {
		return corteCobroPorCilindraje;
	}

}
