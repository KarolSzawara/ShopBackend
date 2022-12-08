package pl.polsl.shopserver.model.entities.dbview;

import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.Instant;

/**
 * Mapping for DB view
 */
@Entity
@Immutable
@Table(name = "order_history")
@Subselect("SELECT " +
        "        o.id_order AS id_order," +
        "        o.order_date AS order_date," +
        "        o.id_user AS id_user," +
        "        oi.id_order_item AS id_order_item," +
        "        oi.order_item_quantity AS order_item_quantity," +
        "        p.product_name AS product_name," +
        "        p.product_description AS product_description" +
        "    FROM" +
        "        ((order o" +
        "        JOIN order_item oi ON (o.id_order = oi.id_order))\n" +
        "        JOIN product p ON (p.id_product = oi.product_id))")
public class OrderHistory {
    @Id
    @Column(name = "id_order", nullable = false)
    private Integer idOrder;

    @Column(name = "order_date")
    private Instant orderDate;

    @Column(name = "id_user")
    private Integer idUser;

    @Column(name = "id_order_item", nullable = false)
    private Integer idOrderItem;

    @Column(name = "order_item_quantity")
    private Integer orderItemQuantity;

    @Column(name = "order_item_price")
    private Double orderItemPrice;

    @Column(name = "product_name", length = 45)
    private String productName;

    @Column(name = "product_description", length = 60)
    private String productDescription;

    public Integer getIdOrder() {
        return idOrder;
    }

    public Instant getOrderDate() {
        return orderDate;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public Integer getIdOrderItem() {
        return idOrderItem;
    }

    public Integer getOrderItemQuantity() {
        return orderItemQuantity;
    }

    public Double getOrderItemPrice() {
        return orderItemPrice;
    }

    public String getProductName() {
        return productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    protected OrderHistory() {
    }
}