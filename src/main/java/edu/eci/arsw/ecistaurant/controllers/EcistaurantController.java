package edu.eci.arsw.ecistaurant.controllers;

import edu.eci.arsw.ecistaurant.persistence.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/")
public class EcistaurantController {

    @Autowired
    private UsuarioRepository studentRepo;

    public String getMensaje() {
        return "Estamos Trabajando en ECI-STAURANT ;)";

    }
}
