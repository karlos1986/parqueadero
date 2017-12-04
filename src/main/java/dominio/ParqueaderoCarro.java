package dominio;

public class ParqueaderoCarro extends Parqueadero{
	
	private static final int CELDAS = 20;
	private static final double VALOR_HORA = 1000;
	private static final double VALOR_DIA = 8000;
	private static final int CORTE_COBRO_POR_HORAS = 9;

	public ParqueaderoCarro() {
		super();
		this.celdas=CELDAS;
		this.valorDia=VALOR_DIA;
		this.valorHora=VALOR_HORA;
		this.corteCobroPorHoras=CORTE_COBRO_POR_HORAS;	
	}

}
