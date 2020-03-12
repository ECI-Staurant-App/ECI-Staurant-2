package edu.eci.arsw.ecistaurant.controllers;

import edu.eci.arsw.ecistaurant.model.Usuario;
import edu.eci.arsw.ecistaurant.persistence.EcistaurantPersistenceException;
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
            List<Usuario> usuarios = studentServices.getAllStudents();
            return new ResponseEntity<>(usuarios, HttpStatus.ACCEPTED);
        }catch (EcistaurantPersistenceException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<?> addNewCar(@RequestBody Usuario usuario){
        try{
            studentServices.saveStudent(usuario);
            return new ResponseEntity<>(usuario,HttpStatus.CREATED);
        }catch (EcistaurantPersistenceException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
}
