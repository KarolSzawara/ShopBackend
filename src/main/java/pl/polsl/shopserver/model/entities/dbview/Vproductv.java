package pl.polsl.shopserver.model.entities.dbview;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Mapping for DB view
 */
@NoArgsConstructor
@Getter
@Entity
@Immutable
@Subselect(" SELECT \n" +
        "        `p`.`id_product` AS `id_product`," +
        "        `p`.`product_description` AS `product_description`," +
        "        `p`.`product_details` AS `product_details`," +
        "        `p`.`product_height` AS `product_height`," +
        "        `p`.`product_name` AS `product_name`," +
        "        `p`.`product_number` AS `product_number`," +
        "        `p`.`product_prize` AS `product_prize`," +
        "        `p`.`product_weight` AS `product_weight`," +
        "        `p`.`product_width` AS `product_width`," +
        "        `p`.`productcol_depth` AS `productcol_depth`," +
        "        `ph`.`src_photo` AS `src_photo`," +
        "        `w`.`quantity_product` AS `quantity_product`" +
        "    FROM " +
        "        ((`product` `p`" +
        "        JOIN `photo` `ph` ON (`p`.`id_product` = `ph`.`id_product`))" +
        "        JOIN `warehouse` `w` ON (`w`.`id_product_wr` = `p`.`id_product`))")
public class Vproductv {
    @Id
    @Column(name = "id_product", nullable = false)
    private Integer idProduct;

    @Column(name = "product_description", length = 45)
    private String productDescription;

    @Column(name = "product_details", length = 45)
    private String productDetails;

    @Column(name = "product_height", length = 45)
    private String productHeight;

    @Column(name = "product_name", length = 45)
    private String productName;

    @Column(name = "product_number")
    private Integer productNumber;

    @Column(name = "product_prize")
    private Double productPrize;

    @Column(name = "product_weight", length = 45)
    private String productWeight;

    @Column(name = "product_width", length = 45)
    private String productWidth;

    @Column(name = "productcol_depth", length = 45)
    private String productcolDepth;

    @Column(name = "src_photo", length = 100)
    private String srcPhoto;

    @Column(name = "quantity_product")
    private Integer quantityProduct;

    public Integer getIdProduct() {
        return idProduct;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public String getProductDetails() {
        return productDetails;
    }

    public String getProductHeight() {
        return productHeight;
    }

    public String getProductName() {
        return productName;
    }

    public Integer getProductNumber() {
        return productNumber;
    }

    public Double getProductPrize() {
        return productPrize;
    }

    public String getProductWeight() {
        return productWeight;
    }

    public String getProductWidth() {
        return productWidth;
    }

    public String getProductcolDepth() {
        return productcolDepth;
    }

    public String getSrcPhoto() {
        return srcPhoto;
    }

    public Integer getQuantityProduct() {
        return quantityProduct;
    }


}