package com.cursojava.empleadosCRUD.controler;

import com.cursojava.empleadosCRUD.model.Info;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoutingController {

    @GetMapping(value = "saludo/{x}/{y}")
    public String saludar(@PathVariable ("x") String a, @PathVariable ("y") String b){
        return "Hola desde Spring " + a + " y  " + b;
    }

    /**
     * Funcion que devuelve datos en formato JSON
     * @param a
     * @param b
     * @return
     */
    @GetMapping(value = "saludo2/{x}/{y}")
    public String saludar2(@RequestParam("x") String a, @RequestParam ("y") String b){
        return "Hola desde Spring " + a + " y  " + b;
    }

    @GetMapping(value = "infoCliente",produces = MediaType.APPLICATION_JSON_VALUE)
    public Info informacion(){
        return new Info("Jaime","jaime@kesar",12);
    }
}
