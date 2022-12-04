package pl.polsl.shopserver.dbentity;

import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.math.BigDecimal;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "order_item")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_order_item", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "id_order")
    private Order idOrder;


    @Column(name = "order_item_quantity")
    private Integer orderItemQuantity;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "order_item_price")
    private Double orderItemPrice;

    public OrderItem(Order idOrder, Integer orderItemQuantity, Double orderItemPrice, Product product) {
        this.idOrder = idOrder;
        this.orderItemQuantity = orderItemQuantity;
        this.orderItemPrice = orderItemPrice;
        this.product = product;
    }
}