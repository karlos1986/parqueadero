package servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import dominio.repositorio.RepositorioVehiculo;

@Service
public class Servicio {

    @Autowired
    private RepositorioVehiculo repositorioVehiculo;

    public Servicio(RepositorioVehiculo repositorioVehiculo) {
        this.repositorioVehiculo = repositorioVehiculo;
    }

    public static String welcome(String userName) {
        return ("hola" + userName);
    }

}
