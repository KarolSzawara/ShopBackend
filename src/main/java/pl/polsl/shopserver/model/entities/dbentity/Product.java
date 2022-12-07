package pl.polsl.shopserver.model.entities.dbentity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_product", nullable = false)
    private Integer id;

    @JsonBackReference
    @ManyToOne( fetch = FetchType.LAZY)
    @JoinColumn(name = "id_category")
    private Category idCategory;

    @Column(name = "product_number")
    private Integer productNumber;

    @Column(name = "product_vat", length = 45)
    private String productVat;



    @Column(name = "product_weight", length = 45)
    private String productWeight;

    @Column(name = "product_width", length = 45)
    private String productWidth;

    @Column(name = "product_height", length = 45)
    private String productHeight;

    @Column(name = "productcol_depth", length = 45)
    private String productcolDepth;

    @Column(name = "product_name", length = 45)
    private String productName;

    @Column(name = "product_description", length = 60)
    private String productDescription;

    @Column(name = "product_details", length = 45)
    private String productDetails;

    @Column(name = "product_prize")
    private Double productPrize;

    public Product(Category idCategory, Integer productNumber, String productVat, Double productPrize, String productWeight, String productWidth, String productHeight, String productcolDepth, String productName, String productDescription, String productDetails) {
        this.idCategory = idCategory;
        this.productNumber = productNumber;
        this.productVat = productVat;
        this.productPrize = productPrize;
        this.productWeight = productWeight;
        this.productWidth = productWidth;
        this.productHeight = productHeight;
        this.productcolDepth = productcolDepth;
        this.productName = productName;
        this.productDescription = productDescription;
        this.productDetails = productDetails;
    }
}