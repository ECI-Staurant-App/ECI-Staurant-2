package edu.eci.arsw.ecistaurant.persistence;
import edu.eci.arsw.ecistaurant.model.Platillo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PlatilloRepository extends JpaRepository<Platillo,Integer> {

    List<Platillo> findAll();

    Optional<Platillo> findById(int id);
}
