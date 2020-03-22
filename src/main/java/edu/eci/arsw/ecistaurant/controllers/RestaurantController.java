package edu.eci.arsw.ecistaurant.controllers;

import edu.eci.arsw.ecistaurant.model.Menu;
import edu.eci.arsw.ecistaurant.model.Pedido;
//import edu.eci.arsw.ecistaurant.model.Platillo;
import edu.eci.arsw.ecistaurant.model.Restaurante;
import edu.eci.arsw.ecistaurant.model.Usuario;
import edu.eci.arsw.ecistaurant.persistence.EcistaurantPersistenceException;
import edu.eci.arsw.ecistaurant.services.ServiciosEstudiante;
import edu.eci.arsw.ecistaurant.services.ServiciosRestaurante;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/restaurants")
public class RestaurantController {

    @Autowired
    private ServiciosRestaurante serviciosRestaurante;
    @Autowired
    private ServiciosEstudiante serviciosEstudiante;



    @Secured({"ROLE_USER","ROLE_ADMIN"})
    @GetMapping("/")
    public ResponseEntity<?>  getAllRestaurants(){
        try{
            List<Restaurante> restaurantes = serviciosRestaurante.getAllRestaurants();
            return new ResponseEntity<>(restaurantes, HttpStatus.ACCEPTED);
        }catch (EcistaurantPersistenceException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @Secured({"ROLE_USER","ROLE_ADMIN"})
    @GetMapping("/{orders}")
    public ResponseEntity<?>  getAllOrders(){
        try{
            List<Pedido> pedidos = serviciosRestaurante.getAllPedidos();
            return new ResponseEntity<>(pedidos, HttpStatus.ACCEPTED);
        }catch (EcistaurantPersistenceException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }
    @Secured({"ROLE_USER","ROLE_ADMIN"})
    @GetMapping("/{user}")
    public ResponseEntity<?>  getOrderByUser(@PathVariable int user){
        try{
            serviciosEstudiante.getStudentById(user);
            return new ResponseEntity<>(serviciosRestaurante.getPedidosByUser(user), HttpStatus.ACCEPTED);
        }catch (EcistaurantPersistenceException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @Secured({"ROLE_USER","ROLE_ADMIN"})
    @GetMapping("/{lastOrders}")
    public ResponseEntity<?>  getLastOrders(){
        return new ResponseEntity<>(serviciosRestaurante.getPedidosByFecha(), HttpStatus.ACCEPTED);
    }

    @Secured({"ROLE_USER","ROLE_ADMIN"})
    @RequestMapping(value = "/AddMenu", method = RequestMethod.POST)
    public ResponseEntity<?> addMenu(String menu,int precio){

        try{
            serviciosRestaurante.saveMenu(menu, precio);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (EcistaurantPersistenceException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/menu", method = RequestMethod.GET)
    public ResponseEntity<?>  getAllMenuByRestaurant(@PathVariable String restaurante) {
        List<Menu> menus = serviciosRestaurante.getAllMenuByRestaurant(restaurante);
        return new ResponseEntity<>(menus, HttpStatus.ACCEPTED);
    }
}




