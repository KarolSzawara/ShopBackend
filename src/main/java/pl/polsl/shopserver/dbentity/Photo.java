package pl.polsl.shopserver.dbentity;

import lombok.*;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "photo")
public class Photo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_photo", nullable = false)
    private Integer id;

    @Column(name = "src_photo", length = 100)
    private String srcPhoto;

    @ManyToOne(fetch = FetchType.LAZY,cascade = {CascadeType.ALL})
    @JoinColumn(name = "id_product")
    private Product idProduct;

    public Photo(String srcPhoto, Product idProduct) {
        this.srcPhoto = srcPhoto;
        this.idProduct = idProduct;
    }
}