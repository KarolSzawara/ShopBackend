package pl.polsl.shopserver;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.Immutable;

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
@Table(name = "orderhistory")
public class Orderhistory {
    @Id
    @Column(name = "id_order", nullable = false)
    private Integer idOrder;

    @Column(name = "order_date")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "UTC")
    private Instant orderDate;

    @Column(name = "id_user")
    private Integer idUser;

    @Column(name = "price")
    private Double price;

    public Integer getIdOrder() {
        return idOrder;
    }

    public Instant getOrderDate() {
        return orderDate;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public Double getPrice() {
        return price;
    }

    protected Orderhistory() {
    }
}