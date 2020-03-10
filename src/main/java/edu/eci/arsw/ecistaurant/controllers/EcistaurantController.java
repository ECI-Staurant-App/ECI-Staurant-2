package edu.eci.arsw.ecistaurant.controllers;

import edu.eci.arsw.ecistaurant.Repository.StudentRepository;
import edu.eci.arsw.ecistaurant.model.Estudiante;
import edu.eci.arsw.ecistaurant.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;

@RestController
@RequestMapping(value = "/")
public class EcistaurantController {

    @Autowired
    private StudentRepository studentRepo;

    public String getMensaje() {
        return "Estamos Trabajando en ECI-STAURANT ;)";

    }
}
