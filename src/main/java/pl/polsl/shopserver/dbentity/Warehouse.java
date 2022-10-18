package pl.polsl.shopserver.dbentity;

import lombok.*;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "warehouse")
public class Warehouse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_warehouse", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_product_wr")
    private Product idProductWr;

    @Column(name = "quantity_product", length = 45)
    private String quantityProduct;

    @Column(name = "prize_warehouse", length = 45)
    private String prizeWarehouse;


}