package pl.polsl.shopserver.JsonEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import pl.polsl.shopserver.model.entities.dbentity.OrderItem;

import java.time.Instant;
import java.util.List;
@Getter
@Setter
@AllArgsConstructor
public class OrderHistoryResponse {
    private Integer id_order;
    private Instant orderDate;
    private List<OrderItem> orderItems;
}
