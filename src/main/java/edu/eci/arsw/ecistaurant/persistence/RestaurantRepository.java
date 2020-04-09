package edu.eci.arsw.ecistaurant.persistence;

        import edu.eci.arsw.ecistaurant.model.Menu;
        import edu.eci.arsw.ecistaurant.model.Restaurante;
        import org.springframework.data.jpa.repository.JpaRepository;
        import org.springframework.data.jpa.repository.Query;
        import org.springframework.stereotype.Repository;

        import java.util.List;
        import java.util.Optional;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurante,Integer> {

    List<Restaurante> findAll();

    Restaurante save(Restaurante restaurante);

    Optional<Restaurante> findById(int id);

    Optional<Restaurante> findByNombre(String name);

}
