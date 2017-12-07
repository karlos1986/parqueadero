package dominio.excepcion;

public class IngresarExcepcion extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public IngresarExcepcion(String message) {
		super(message);
	}

}
