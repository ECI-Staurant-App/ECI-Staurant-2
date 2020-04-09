package edu.eci.arsw.ecistaurant.services;

import edu.eci.arsw.ecistaurant.model.Menu;
import edu.eci.arsw.ecistaurant.model.Pedido;
//import edu.eci.arsw.ecistaurant.model.Platillo;
import edu.eci.arsw.ecistaurant.model.Restaurante;
import edu.eci.arsw.ecistaurant.persistence.EcistaurantPersistenceException;

import java.util.List;
import java.util.Optional;


public interface ServiciosRestaurante {

    List<Restaurante> getAllRestaurants() throws EcistaurantPersistenceException;

    void saveRestaurant(Restaurante restaurante) throws EcistaurantPersistenceException;

    Restaurante getRestaurantById(int id) throws EcistaurantPersistenceException;

    List<Pedido> getAllPedidos() throws EcistaurantPersistenceException;

    Pedido getPedidoById(int id) throws EcistaurantPersistenceException;

    void saveMenu(String menu,int precio) throws EcistaurantPersistenceException;

    List<Menu> getMenusByRestaurant(String restaurante) throws EcistaurantPersistenceException;

    List<Pedido> getPedidosByUser(int user);

    List<Pedido> getPedidosByFecha();

    List<Pedido> getPedidosByRestaurant(String restaurant) throws EcistaurantPersistenceException;


    // void savePlatillo(Platillo platillo) throws EcistaurantPersistenceException;

   // List<Platillo> getAllPlatillos() throws EcistaurantPersistenceException;

   // Platillo getPlatilloById(int id ) throws EcistaurantPersistenceException;

}
