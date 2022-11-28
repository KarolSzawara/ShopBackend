package pl.polsl.shopserver.dbentity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cart")
@Data
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cart", nullable = false)
    private Integer id;

    @Column(name = "order_item_quantity")
    private Integer orderItemQuantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_use")
    private User idUse;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_prod")
    private Product idProd;

    public Product getIdProd() {
        return idProd;
    }

    public void setIdProd(Product idProd) {
        this.idProd = idProd;
    }

    public User getIdUse() {
        return idUse;
    }

    public void setIdUse(User idUse) {
        this.idUse = idUse;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public Cart(Integer orderItemQuantity, User idUse, Product idProd) {
        this.orderItemQuantity = orderItemQuantity;
        this.idUse = idUse;
        this.idProd = idProd;
    }
}