package edu.eci.arsw.EciStaurant.Repository;

import edu.eci.arsw.EciStaurant.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido,Integer> {
}
