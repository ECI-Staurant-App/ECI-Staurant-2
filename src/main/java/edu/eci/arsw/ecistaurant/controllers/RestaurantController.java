package edu.eci.arsw.ecistaurant.controllers;

import edu.eci.arsw.ecistaurant.model.Menu;
import edu.eci.arsw.ecistaurant.model.Pedido;
import edu.eci.arsw.ecistaurant.model.Restaurante;
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
        List<Restaurante> restaurantes = serviciosRestaurante.getAllRestaurants();
        return new ResponseEntity<>(restaurantes, HttpStatus.ACCEPTED);
    }

    @Secured({"ROLE_USER","ROLE_ADMIN"})
    @GetMapping("/{user}")
    public ResponseEntity<?>  getOrderByUser(@PathVariable ("user") int user){
        try{
            serviciosEstudiante.getStudentById(user);
            return new ResponseEntity<>(serviciosRestaurante.getPedidosByUser(user), HttpStatus.ACCEPTED);
        }catch (EcistaurantPersistenceException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @Secured({"ROLE_USER","ROLE_ADMIN"})
    @GetMapping("/{id}")
    public ResponseEntity<?>  getOrderById(@PathVariable int id){
        try{
            serviciosRestaurante.getPedidoById(id);
            return new ResponseEntity<>(serviciosRestaurante.getPedidoById(id), HttpStatus.ACCEPTED);
        }catch (EcistaurantPersistenceException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }


    @Secured({"ROLE_USER","ROLE_ADMIN"})
    @RequestMapping(value = "/{pedido}", method = RequestMethod.PUT)
    public ResponseEntity<?> changeOrderState(@PathVariable("pedido")int pedido , @RequestBody String estado) {
        try {
            serviciosRestaurante.changeOrderState(estado,pedido);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } catch (EcistaurantPersistenceException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
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

    @Secured({"ROLE_USER","ROLE_ADMIN"})
    @RequestMapping(value = "/{restaurant}/menus", method = RequestMethod.GET)
    public ResponseEntity<?> getMenusByRestaurant(@PathVariable ("restaurant") String restaurant){
        try{

            List<Menu> menus = serviciosRestaurante.getMenusByrestaurant(restaurant);
            return new ResponseEntity<>(menus,HttpStatus.ACCEPTED);
        }catch (EcistaurantPersistenceException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @Secured({"ROLE_USER","ROLE_ADMIN"})
    @RequestMapping(value = "/{restaurant}/orders", method = RequestMethod.GET)
    public ResponseEntity<?> getOrdersByRestaurant(@PathVariable ("restaurant") String restaurant){
        try{

            List<Pedido> pedidos =serviciosRestaurante.getPedidosByRestaurant(restaurant);
            return new ResponseEntity<>(pedidos,HttpStatus.ACCEPTED);
        }catch (EcistaurantPersistenceException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

}




