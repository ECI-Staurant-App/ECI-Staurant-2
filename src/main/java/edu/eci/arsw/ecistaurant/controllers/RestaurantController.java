package edu.eci.arsw.ecistaurant.controllers;

import edu.eci.arsw.ecistaurant.model.Pedido;
import edu.eci.arsw.ecistaurant.model.Platillo;
import edu.eci.arsw.ecistaurant.model.Usuario;
import edu.eci.arsw.ecistaurant.persistence.EcistaurantPersistenceException;
import edu.eci.arsw.ecistaurant.services.ServiciosEstudiante;
import edu.eci.arsw.ecistaurant.services.ServiciosRestaurante;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/restaurants")
public class RestaurantController {

    @Autowired
    private ServiciosRestaurante serviciosRestaurante;
    @Autowired
    private ServiciosEstudiante serviciosEstudiante;


    @GetMapping("/")
    public ResponseEntity<?>  getAllOrders(){
        try{
            List<Pedido> pedidos = serviciosRestaurante.getAllPedidos();
            return new ResponseEntity<>(pedidos, HttpStatus.ACCEPTED);
        }catch (EcistaurantPersistenceException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{user}")
    public ResponseEntity<?>  getOrderByUser(@PathVariable int user){
        try{
            serviciosEstudiante.getStudentById(user);
            return new ResponseEntity<>(serviciosRestaurante.getPedidosByUser(user), HttpStatus.ACCEPTED);
        }catch (EcistaurantPersistenceException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/lastOrders}")
    public ResponseEntity<?>  getLastOrders(){
        return new ResponseEntity<>(serviciosRestaurante.getPedidosByFecha(), HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/AddPlatiilo", method = RequestMethod.POST)
    public ResponseEntity<?> addPlatillo(Platillo platillo){
        try{
            serviciosRestaurante.savePlatillo(platillo);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (EcistaurantPersistenceException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }


}




