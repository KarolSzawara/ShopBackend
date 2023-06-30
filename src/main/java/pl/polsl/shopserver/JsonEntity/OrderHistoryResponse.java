package pl.polsl.shopserver.JsonEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import pl.polsl.shopserver.model.entities.dbentity.OrderItem;

import java.time.Instant;
import java.util.List;

public record OrderHistoryResponse(Integer id_order,Instant orderDate,List<OrderItem> orderItems) {

}

