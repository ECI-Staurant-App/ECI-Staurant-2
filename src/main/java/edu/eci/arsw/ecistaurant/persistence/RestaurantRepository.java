package edu.eci.arsw.ecistaurant.persistence;

import edu.eci.arsw.ecistaurant.model.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RestaurantRepository extends JpaRepository<Restaurante,Integer> {

    List<Restaurante> findAll();

    Optional<Restaurante> findById(int id);

}
