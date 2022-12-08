package pl.polsl.shopserver.OrderItem;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.polsl.shopserver.model.entities.dbentity.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem,Integer> {
}
