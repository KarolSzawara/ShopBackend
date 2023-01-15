package pl.polsl.shopserver.model.entities.dbentity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @Column(name = "src_photo", length = 150)
    private String srcPhoto;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_product")
    private Product idProduct;

    public Photo(String srcPhoto, Product idProduct) {
        this.srcPhoto = srcPhoto;
        this.idProduct = idProduct;
    }
}