package pl.polsl.shopserver.Cart;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EditCart {
    Integer cartID;
    Integer productQuantity;
}
