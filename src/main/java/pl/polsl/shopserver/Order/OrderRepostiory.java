package pl.polsl.shopserver.Order;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.polsl.shopserver.model.entities.dbentity.Order;

public interface OrderRepostiory extends JpaRepository<Order,Integer> {
}
