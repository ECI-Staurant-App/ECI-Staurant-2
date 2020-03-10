package edu.eci.arsw.ecistaurant.services;

import edu.eci.arsw.ecistaurant.model.Restaurante;
import edu.eci.arsw.ecistaurant.persistence.EcistaurantPersistenceException;

import java.util.List;
import java.util.Optional;

public interface ServiciosRestaurante {

    List<Restaurante> getAllRestaurants() throws EcistaurantPersistenceException;

    void saveRestaurant(Restaurante restaurante) throws EcistaurantPersistenceException;

    Restaurante getRestaurantById(int id) throws EcistaurantPersistenceException;
}
