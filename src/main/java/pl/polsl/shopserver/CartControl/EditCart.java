package pl.polsl.shopserver.CartControl;

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
