package edu.eci.arsw.ecistaurant.persistence;

import edu.eci.arsw.ecistaurant.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido,Integer> {

    List<Pedido> findAll();

    Pedido save(Pedido pedido);

    Optional<Pedido> findById(int id);

    @Query(value = "SELECT * from pedido where estudiante = ?1 ORDER BY fecha DESC ",nativeQuery = true)
    List<Pedido> findAllByUsuario(int carne);

    @Query(value = "SELECT * from pedido ORDER BY fecha DESC ",nativeQuery = true)
    List<Pedido> findAllByFecha();

    @Query(value="SELECT * from pedido WHERE pedido.restaurante = (SELECT id_restaurante FROM restaurante WHERE nombre= ?1 LIMIT 1)",nativeQuery = true)
    List<Pedido> findOrOrderByRestaurante(String restaurate);
}
