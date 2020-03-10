package edu.eci.arsw.ecistaurant.controllers;

import edu.eci.arsw.ecistaurant.persistence.EcistaurantPersistenceException;
import edu.eci.arsw.ecistaurant.model.Estudiante;
import edu.eci.arsw.ecistaurant.services.ServiciosEstudiante;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/student")
public class StudentController {

    @Autowired
    private ServiciosEstudiante studentServices;

    @GetMapping
    public ResponseEntity<?>  getAllStudents(){
        try{
            List<Estudiante> estudiantes = studentServices.getAllStudents();
            return new ResponseEntity<>(estudiantes, HttpStatus.ACCEPTED);
        }catch (EcistaurantPersistenceException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<?> addNewCar(@RequestBody Estudiante estudiante){
        try{
            studentServices.saveStudent(estudiante);
            return new ResponseEntity<>(estudiante,HttpStatus.CREATED);
        }catch (EcistaurantPersistenceException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
}
