package pl.polsl.shopserver.OrderItem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.polsl.shopserver.model.entities.dbentity.Cart;
import pl.polsl.shopserver.model.entities.dbentity.OrderItem;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem,Integer> {
    @Query(value = "SELECT o FROM OrderItem o where o.idOrder.id=:orderId")
    List<OrderItem> findCartByIdUser(@Param("orderId")Integer orderId);
}
