package edu.eci.arsw.ecistaurant.services;

import edu.eci.arsw.ecistaurant.model.Menu;
import edu.eci.arsw.ecistaurant.model.Pedido;
import edu.eci.arsw.ecistaurant.model.Restaurante;
import edu.eci.arsw.ecistaurant.persistence.EcistaurantPersistenceException;

import java.util.List;



public interface ServiciosRestaurante {

    List<Restaurante> getAllRestaurants();

    void saveRestaurant(Restaurante restaurante) throws EcistaurantPersistenceException;

    Restaurante getRestaurantByName(String restaurante) throws EcistaurantPersistenceException;

    void changeOrderState(String estado,int pedido) throws EcistaurantPersistenceException;

    Pedido getPedidoById(int id) throws EcistaurantPersistenceException;

    void saveMenu(String menu,int precio) throws EcistaurantPersistenceException;

    List<Menu> getMenusByrestaurant(String restaurante) throws EcistaurantPersistenceException;

    List<Pedido> getPedidosByUser(int user);

    List<Pedido> getPedidosByFecha();

    List<Pedido> getPedidosByRestaurant(String restaurant) throws EcistaurantPersistenceException;


}
