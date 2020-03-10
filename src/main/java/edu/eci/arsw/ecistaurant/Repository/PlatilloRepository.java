package edu.eci.arsw.ecistaurant.Repository;
import edu.eci.arsw.ecistaurant.model.Platillo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlatilloRepository extends JpaRepository<Platillo,Integer> {
}
