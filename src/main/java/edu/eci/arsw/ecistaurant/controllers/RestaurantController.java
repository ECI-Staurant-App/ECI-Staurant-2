package edu.eci.arsw.ecistaurant.controllers;

import edu.eci.arsw.ecistaurant.cache.RestaurantCache;
import edu.eci.arsw.ecistaurant.model.Menu;
import edu.eci.arsw.ecistaurant.model.Pedido;
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
    @Autowired
    private RestaurantCache restaurantCache;



    @Secured({"ROLE_USER","ROLE_ADMIN","ROLE_RESTAURANT"})
    @GetMapping("/")
    public ResponseEntity<?>  getAllRestaurants(){
        List<Restaurante> restaurantes = serviciosRestaurante.getAllRestaurants();
        return new ResponseEntity<>(restaurantes, HttpStatus.ACCEPTED);
    }

    @Secured({"ROLE_USER","ROLE_ADMIN","ROLE_RESTAURANT"})
    @GetMapping("/{user}")
    public ResponseEntity<?>  getOrderByUser(@PathVariable ("user") int user){
        try{
            serviciosEstudiante.getStudentById(user);
            return new ResponseEntity<>(serviciosRestaurante.getPedidosByUser(user), HttpStatus.ACCEPTED);
        }catch (EcistaurantPersistenceException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @Secured({"ROLE_USER","ROLE_ADMIN","ROLE_RESTAURANT"})
    @GetMapping("/{restaurant}/orders/{id}")
    public ResponseEntity<?>  getOrderById(@PathVariable int id,@PathVariable String restaurant){
        try{
            serviciosRestaurante.getPedidoByIdAndRestaurant(id,restaurant);
            return new ResponseEntity<>(serviciosRestaurante.getPedidoByIdAndRestaurant(id,restaurant), HttpStatus.ACCEPTED);
        }catch (EcistaurantPersistenceException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }


    @Secured({"ROLE_USER","ROLE_ADMIN","ROLE_RESTAURANT"})
    @RequestMapping(value = "/{restaurante}/orders/{pedido}", method = RequestMethod.PUT)
    public ResponseEntity<?> changeOrderState(@PathVariable("pedido")int pedido,@PathVariable("restaurante")String restaurante , @RequestBody String estado) {
        try {
            serviciosRestaurante.changeOrderState(estado,pedido,restaurante);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } catch (EcistaurantPersistenceException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @Secured({"ROLE_USER","ROLE_ADMIN","ROLE_RESTAURANT"})
    @GetMapping("/{lastOrders}")
    public ResponseEntity<?>  getLastOrders(){
        return new ResponseEntity<>(serviciosRestaurante.getPedidosByFecha(), HttpStatus.ACCEPTED);
    }

    @Secured({"ROLE_USER","ROLE_ADMIN","ROLE_RESTAURANT"})
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<?> addRestaurant(String restaurante){
        try{
            serviciosRestaurante.saveRestaurant(restaurante);
            return new ResponseEntity<>(restaurante,HttpStatus.CREATED);
        }catch (EcistaurantPersistenceException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @Secured({"ROLE_USER","ROLE_ADMIN","ROLE_RESTAURANT"})
    @RequestMapping(value = "/AddMenu", method = RequestMethod.POST)
    public ResponseEntity<?> addMenu(String restaurante,String menu,int precio){

        try{
            serviciosRestaurante.saveMenu(restaurante,menu, precio);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (EcistaurantPersistenceException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @Secured({"ROLE_USER","ROLE_ADMIN","ROLE_RESTAURANT"})
    @RequestMapping(value = "/{restaurant}/menus", method = RequestMethod.GET)
    public ResponseEntity<?> getMenusByRestaurant(@PathVariable ("restaurant") String restaurant){
        try{

            List<Menu> menus = serviciosRestaurante.getMenusByrestaurant(restaurant);
            return new ResponseEntity<>(menus,HttpStatus.ACCEPTED);
        }catch (EcistaurantPersistenceException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @Secured({"ROLE_USER","ROLE_ADMIN","ROLE_RESTAURANT"})
    @RequestMapping(value = "/{restaurant}/orders", method = RequestMethod.GET)
    public ResponseEntity<?> getOrdersByRestaurant(@PathVariable ("restaurant") String restaurant){
        try{

            List<Pedido> pedidos = serviciosRestaurante.getPedidosByRestaurant(restaurant);
            return new ResponseEntity<>(pedidos,HttpStatus.ACCEPTED);
        }catch (EcistaurantPersistenceException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

}




