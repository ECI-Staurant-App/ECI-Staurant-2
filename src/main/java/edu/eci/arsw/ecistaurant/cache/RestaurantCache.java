package edu.eci.arsw.ecistaurant.cache;

import edu.eci.arsw.ecistaurant.model.Menu;
import edu.eci.arsw.ecistaurant.model.Pedido;
import edu.eci.arsw.ecistaurant.model.Restaurante;
import edu.eci.arsw.ecistaurant.persistence.EcistaurantPersistenceException;
import edu.eci.arsw.ecistaurant.services.ServiciosEstudiante;
import edu.eci.arsw.ecistaurant.services.ServiciosRestaurante;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RestaurantCache {

    @Autowired
    ServiciosRestaurante serviciosRestaurante;
    @Autowired
    ServiciosEstudiante serviciosEstudiante;

    @Cacheable(value = "restaurantCache")
    public List<Restaurante> getAllRestaurants() {
        System.out.println("-------------------Retrieving RESTAURANTS from Database ---------------------");
        return serviciosRestaurante.getAllRestaurants();
    }

    @Cacheable(value = "menusCache",key = "#restaurante")
    public List<Menu> getMenusByRestaurant(String restaurante) throws EcistaurantPersistenceException {
        System.out.println("-------------------Retrieving MENUS from Database ---------------------");
        return serviciosRestaurante.getMenusByrestaurant(restaurante);
    }

    @Cacheable(value = "pedidosCache",key = "#restaurant")
    public List<Pedido> getOrdersByRestaurant(String restaurant) throws EcistaurantPersistenceException {
        System.out.println("-------------------Retrieving ORDERS from Database ---------------------");
        return serviciosRestaurante.getPedidosByRestaurant(restaurant);
    }


}