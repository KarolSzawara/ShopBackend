package pl.polsl.shopserver.CartControl;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CartItem {
    Integer productID;
    Integer productQuantity;
}
