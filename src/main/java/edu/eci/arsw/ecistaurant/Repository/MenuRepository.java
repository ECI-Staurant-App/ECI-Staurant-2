package edu.eci.arsw.EciStaurant.Repository;

import edu.eci.arsw.EciStaurant.model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<Menu,Integer> {
}
