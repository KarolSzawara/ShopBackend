package pl.polsl.shopserver.dbentity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_category", nullable = false)
    private Integer id;

    @Column(name = "category_name", length = 45)
    private String categoryName;

    @JsonManagedReference
    @OneToMany(mappedBy = "idCategory", fetch = FetchType.EAGER)
    private Set<Product> products = new LinkedHashSet<>();

    public Category(String categoryName, Set<Product> products) {
        this.categoryName = categoryName;
        this.products = products;
    }
}