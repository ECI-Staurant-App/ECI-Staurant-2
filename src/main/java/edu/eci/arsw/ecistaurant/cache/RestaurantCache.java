package edu.eci.arsw.ecistaurant.cache;


import edu.eci.arsw.ecistaurant.model.Pedido;
import edu.eci.arsw.ecistaurant.model.Restaurante;
import edu.eci.arsw.ecistaurant.persistence.EcistaurantPersistenceException;
import edu.eci.arsw.ecistaurant.persistence.RestaurantRepository;
import edu.eci.arsw.ecistaurant.services.ServiciosRestaurante;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RestaurantCache {

    @Autowired
    ServiciosRestaurante serviciosRestaurante;

    @Cacheable(value = "restaurantCache")
    public List<Restaurante> getAllRestaurants() {
        System.out.println("-------------------Retrieving RESTAURANTS from Database ---------------------");
        return serviciosRestaurante.getAllRestaurants();
    }

    @Cacheable(value = "pedidosCache",key = "#name")
    public List<Pedido> getPedidosByRestaurant(String name) throws EcistaurantPersistenceException {
        System.out.println("-------------------Retrieving ORDERS from Database ---------------------");
        return serviciosRestaurante.getPedidosByRestaurant(name);
    }
}