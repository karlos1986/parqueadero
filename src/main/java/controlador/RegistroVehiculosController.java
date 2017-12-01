package controlador;
import servicio.Servicio;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegistroVehiculosController {
	
	@Autowired
    private Servicio servicio;
 
    public RegistroVehiculosController(Servicio servicio) {
        this.servicio = servicio;
    }
 
    @RequestMapping(value = "/welcome/{userName}", method = RequestMethod.GET)
    public String welcome(@PathVariable("userName") String userName) 
    {
        return Servicio.welcome(userName);
    }
}
	

