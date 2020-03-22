package edu.eci.arsw.ecistaurant.controllers;

import edu.eci.arsw.ecistaurant.model.Menu;
import edu.eci.arsw.ecistaurant.model.Pedido;
import edu.eci.arsw.ecistaurant.model.Usuario;
import edu.eci.arsw.ecistaurant.persistence.EcistaurantPersistenceException;
import edu.eci.arsw.ecistaurant.services.ServiciosEstudiante;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class StudentController {

    @Autowired
    private ServiciosEstudiante studentServices;

    @Secured({"ROLE_USER","ROLE_ADMIN"})
    @GetMapping("/")
    public ResponseEntity<?>  getAllUsers(){
        try{
            List<Usuario> usuarios = studentServices.getAllStudents();
            return new ResponseEntity<>(usuarios, HttpStatus.ACCEPTED);
        }catch (EcistaurantPersistenceException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }
    @Secured({"ROLE_USER","ROLE_ADMIN"})
    @GetMapping("/{carne}")
    public ResponseEntity<?>  getUserById(@PathVariable int carne){
        try{
            return new ResponseEntity<>(studentServices.getStudentById(carne), HttpStatus.ACCEPTED);
        }catch (EcistaurantPersistenceException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }
    @Secured({"ROLE_USER","ROLE_ADMIN"})
    @GetMapping("/{mesas}")
    public ResponseEntity<?>  getTables(){
        return new ResponseEntity<>(studentServices.buscarMesas(), HttpStatus.ACCEPTED);
    }
    @Secured({"ROLE_USER","ROLE_ADMIN"})
    @GetMapping("/{mesaDisponibles}")
    public ResponseEntity<?>  getAvailableTables(){
        return new ResponseEntity<>(studentServices.buscarMesasDisponibles(), HttpStatus.ACCEPTED);
    }

    @Secured({"ROLE_USER","ROLE_ADMIN"})
    @PostMapping("/")
    public ResponseEntity<?> addUser(@RequestBody Usuario usuario){
        try{
            studentServices.saveStudent(usuario);
            return new ResponseEntity<>(usuario,HttpStatus.CREATED);
        }catch (EcistaurantPersistenceException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @Secured({"ROLE_USER","ROLE_ADMIN"})
    @RequestMapping(value = "/AddOrder", method = RequestMethod.POST)
    public ResponseEntity<?> placeOrder(int user,String restaurante, String platillo){
        try{
            studentServices.realizarPedido(user,restaurante,platillo);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (EcistaurantPersistenceException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
    @Secured({"ROLE_USER","ROLE_ADMIN"})
    @PutMapping(value = "/{carne}")
    public ResponseEntity<?> putUser(@PathVariable int carne,@RequestBody Usuario usuario) {
        try {
            studentServices.actualizarSaldo(usuario);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (EcistaurantPersistenceException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}




