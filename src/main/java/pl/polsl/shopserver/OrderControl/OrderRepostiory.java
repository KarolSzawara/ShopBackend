package pl.polsl.shopserver.OrderControl;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.polsl.shopserver.dbentity.Order;

public interface OrderRepostiory extends JpaRepository<Order,Integer> {
}
