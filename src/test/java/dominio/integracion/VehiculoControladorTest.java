package dominio.integracion;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import aplicacion.Application;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
@AutoConfigureMockMvc
public class VehiculoControladorTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void postIngresoCarroRetornaStatus201() throws Exception {
 
        mockMvc.perform(post("/ingreso/carro")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"placa\" : \"TWA925\",\"tipo\" : 1 }"))
                .andExpect(status().isOk()).andExpect(jsonPath("status").value(201));;

    }
    
    @Test
    public void postIngresoMotoRetornaStatus201() throws Exception {
 
        mockMvc.perform(post("/ingreso/moto")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"placa\" : \"TWA625\",\"tipo\" : 2,\"cilindraje\" : 600 }"))
                .andExpect(status().isOk()).andExpect(jsonPath("status").value(201));

    }
    
    @Test
    public void postIngresoMotoRepetidaRetornaStatus403() throws Exception {
 
    	mockMvc.perform(post("/ingreso/moto")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"placa\" : \"TWA325\",\"tipo\" : 2,\"cilindraje\" : 600 }"));
    	mockMvc.perform(post("/ingreso/moto")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"placa\" : \"TWA325\",\"tipo\" : 2,\"cilindraje\" : 600 }"))
                .andExpect(status().isOk()).andExpect(jsonPath("status").value(403))
                .andExpect(jsonPath("entity").value("El vehiculo se encuentra registrado en el parqueadero"));
    }
    
    @Test
    public void postIngresoCarroRepetidaRetornaStatus403() throws Exception {
 
    	mockMvc.perform(post("/ingreso/carro")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"placa\" : \"TWA825\",\"tipo\" : 1 }"));
    	mockMvc.perform(post("/ingreso/carro")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"placa\" : \"TWA825\",\"tipo\" : 1 }"))
                .andExpect(status().isOk()).andExpect(jsonPath("status").value(403))
                .andExpect(jsonPath("entity").value("El vehiculo se encuentra registrado en el parqueadero"));
    }
    
    @Test
    public void getCobroMotoNoRegistradaRetornaStatus404() throws Exception {
 
    	mockMvc.perform(get("/pago?placa=","TEA325"))
                .andExpect(status().isOk()).andExpect(jsonPath("status").value(404))
                .andExpect(jsonPath("entity").value("No se encontro registro"));
    }
    
    @Test
    public void getCobroMotoRegistradaRetornaStatus200() throws Exception {
 
    	mockMvc.perform(post("/ingreso/moto")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"placa\" : \"PRA325\",\"tipo\" : 2,\"cilindraje\" : 600 }"));
    	mockMvc.perform(get("/pago?placa=PRA325"))
                .andExpect(status().isOk()).andExpect(jsonPath("status").value(200));
    }
    
    
}



