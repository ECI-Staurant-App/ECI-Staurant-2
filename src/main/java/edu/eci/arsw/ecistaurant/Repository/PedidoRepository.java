package edu.eci.arsw.ecistaurant.Repository;

import edu.eci.arsw.ecistaurant.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido,Integer> {
}
