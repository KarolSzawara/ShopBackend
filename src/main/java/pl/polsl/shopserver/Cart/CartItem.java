package pl.polsl.shopserver.Cart;

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
