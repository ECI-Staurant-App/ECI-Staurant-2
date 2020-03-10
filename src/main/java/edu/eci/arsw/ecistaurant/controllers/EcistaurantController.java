package edu.eci.arsw.EciStaurant.controllers;

import edu.eci.arsw.EciStaurant.Repository.StudentRepository;
import edu.eci.arsw.EciStaurant.model.Estudiante;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;

@RestController
@RequestMapping(value = "/")
public class EcistaurantController {

    @Autowired
    private StudentRepository studentRepo;

    private EntityManager entityManager;

    public EcistaurantController() {
    }

    public void InsertarEstudiante(Estudiante est){


        entityManager.createNativeQuery("INSERT INTO estudiante (carne,nombre,saldo,email,passwd) VALUES (?,?,?)")

                .setParameter(1,est.getCarne())
                .setParameter(2,est.getName())
                .setParameter(3,est.getSaldo())
                .setParameter(4,est.getEmail())
                .setParameter(5,est.getPasswd())
                .executeUpdate();

    }








    @RequestMapping(method = RequestMethod.GET)
    public String getMensaje() {
        return "Estamos Trabajando en ECI-STAURANT ;)";

    }
}
