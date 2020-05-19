package edu.eci.arsw.ecistaurant.controllers;

import edu.eci.arsw.ecistaurant.model.Menu;
import edu.eci.arsw.ecistaurant.model.Usuario;
import edu.eci.arsw.ecistaurant.persistence.EcistaurantPersistenceException;
import edu.eci.arsw.ecistaurant.services.EcistaurantLogicException;
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
    @RequestMapping(value = "/{usuario}", method = RequestMethod.GET)
    public ResponseEntity<?>  getUserById(@PathVariable ("usuario") int usuario){
        try{
            return new ResponseEntity<>(studentServices.getStudentById(usuario), HttpStatus.ACCEPTED);
        }catch (EcistaurantPersistenceException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }
    @Secured({"ROLE_USER","ROLE_ADMIN"})
    @RequestMapping(value = "/Alltables", method = RequestMethod.GET)
    public ResponseEntity<?>  getTables(){
        return new ResponseEntity<>(studentServices.buscarMesas(), HttpStatus.ACCEPTED);
    }
    @Secured({"ROLE_USER","ROLE_ADMIN"})
    @RequestMapping(value = "/tables", method = RequestMethod.GET)
    public ResponseEntity<?>  getAvailableTables(){
        return new ResponseEntity<>(studentServices.buscarMesasDisponibles(), HttpStatus.ACCEPTED);
    }

    @Secured({"ROLE_USER","ROLE_ADMIN"})
    @RequestMapping(value = "/menus", method = RequestMethod.GET)
    public ResponseEntity<?>  getAllMenuByRestaurant(@RequestParam(value="restaurante") String restaurante) {
        List<Menu> menus = studentServices.getAllMenuByRestaurant(restaurante);
        return new ResponseEntity<>(menus, HttpStatus.ACCEPTED);
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
    public ResponseEntity<?> placeOrder(String user,String restaurante, String platillo){
        try{
            studentServices.realizarPedido(user,restaurante,platillo);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (EcistaurantPersistenceException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
    @Secured({"ROLE_USER","ROLE_ADMIN"})
    @PutMapping(value = "/{user}")
    public ResponseEntity<?> updateSaldo(@PathVariable int user,int saldo) {
        try {
            studentServices.actualizarSaldo(user,saldo);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (EcistaurantPersistenceException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Secured({"ROLE_USER","ROLE_ADMIN"})
    @RequestMapping(value = "/{usuario}/LastOrder", method = RequestMethod.GET)
    public ResponseEntity<?>  getLastOrderOfUser(@PathVariable ("usuario") String usuario){
        try{
            return new ResponseEntity<>(studentServices.getLastOrderOfUser(usuario), HttpStatus.ACCEPTED);
        }catch (EcistaurantPersistenceException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @Secured({"ROLE_USER","ROLE_ADMIN"})
    @RequestMapping(value = "/mesa/{mesa}", method = RequestMethod.PUT)
    public ResponseEntity<?> changeTableState(@PathVariable("mesa")int mesa, Boolean estado) {
        try {
            studentServices.cambiarEstadoMesa(mesa,estado);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } catch (EcistaurantPersistenceException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }




}




