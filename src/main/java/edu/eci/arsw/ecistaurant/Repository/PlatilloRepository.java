package edu.eci.arsw.EciStaurant.Repository;

import edu.eci.arsw.EciStaurant.model.Platillo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlatilloRepository extends JpaRepository<Platillo,Integer> {
}
