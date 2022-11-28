package pl.polsl.shopserver.OrderHistoryControl;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.polsl.shopserver.dbentity.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem,Integer> {
}
