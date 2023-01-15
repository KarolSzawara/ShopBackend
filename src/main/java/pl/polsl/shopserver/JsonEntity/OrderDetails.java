package pl.polsl.shopserver.JsonEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.polsl.shopserver.model.entities.dbentity.OrderItem;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class OrderDetails {
    String productName;
    OrderItem orderItem;
}
