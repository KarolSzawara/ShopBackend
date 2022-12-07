package pl.polsl.shopserver.model.entities.dbview;

import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Mapping for DB view
 */
@Entity
@Immutable
@Table(name = "cartlist")
@Subselect(" SELECT " +
        "        c.id_cart AS id_cart," +
        "        c.order_item_quantity AS order_item_quantity," +
        "        c.id_use AS id_use," +
        "        p.id_product AS id_product," +
        "        p.product_name AS product_name," +
        "        p.product_description AS product_description," +
        "        p.product_prize AS product_prize," +
        "        ph.src_photo AS src_photo" +
        "    FROM" +
        "        ((cart c" +
        "        JOIN product p ON (c.id_prod = p.id_product))" +
        "        JOIN photo ph ON (p.id_product = ph.id_product))")
public class Cartlist {
    @Id
    @Column(name = "id_cart", nullable = false)
    private Integer idCart;

    @Column(name = "order_item_quantity")
    private Integer orderItemQuantity;

    @Column(name = "id_use")
    private Integer idUse;

    @Column(name = "id_product", nullable = false)
    private Integer idProduct;

    @Column(name = "product_name", length = 45)
    private String productName;

    @Column(name = "product_description", length = 45)
    private String productDescription;

    @Column(name = "product_prize", length = 45)
    private String productPrize;

    @Column(name = "src_photo", length = 100)
    private String srcPhoto;

    public Integer getIdCart() {
        return idCart;
    }

    public Integer getOrderItemQuantity() {
        return orderItemQuantity;
    }

    public Integer getIdUse() {
        return idUse;
    }

    public Integer getIdProduct() {
        return idProduct;
    }

    public String getProductName() {
        return productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public String getProductPrize() {
        return productPrize;
    }

    public String getSrcPhoto() {
        return srcPhoto;
    }

    protected Cartlist() {
    }
}