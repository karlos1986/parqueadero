package dominio.excepcion;

public class CalcularCobroExcepcion extends RuntimeException  {

	private static final long serialVersionUID = 1L;
	
	public CalcularCobroExcepcion(String message) {
		super(message);
		}

}
