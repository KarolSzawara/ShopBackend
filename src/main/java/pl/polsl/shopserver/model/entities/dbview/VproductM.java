package pl.polsl.shopserver.model.entities.dbview;

import lombok.*;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Mapping for DB view
 */
@NoArgsConstructor
@Getter
@Entity
@Immutable
@Subselect("SELECT " +
        "        p.id_product AS id_product," +
        "        p.product_prize AS product_prize," +
        "        p.product_name AS product_name," +
        "        p.product_description AS product_description," +
        "        c.id_category AS id_category," +
        "        c.category_name AS category_name," +
        "        ph.id_photo AS id_photo," +
        "        ph.src_photo AS src_photo" +
        "    FROM" +
        "        ((product p" +
        "        JOIN category c ON (p.id_category = c.id_category))" +
        "        JOIN photo ph ON (p.id_product = ph.id_product))")
@Table(name = "vproduct_ms")
public class VproductM {
    @Id
    @Column(name = "id_product", nullable = false)
    private Integer idProduct;

    @Column(name = "product_prize")
    private Double productPrize;

    @Column(name = "product_name", length = 45)
    private String productName;

    @Column(name = "product_description", length = 45)
    private String productDescription;

    @Column(name = "id_category", nullable = false)
    private Integer idCategory;

    @Column(name = "category_name", length = 45)
    private String categoryName;

    @Column(name = "id_photo", nullable = false)
    private Integer idPhoto;

    @Column(name = "src_photo", length = 45)
    private String srcPhoto;

}