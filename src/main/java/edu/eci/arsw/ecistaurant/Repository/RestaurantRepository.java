package edu.eci.arsw.ecistaurant.Repository;
import edu.eci.arsw.ecistaurant.model.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<Restaurante,Integer> {
}
