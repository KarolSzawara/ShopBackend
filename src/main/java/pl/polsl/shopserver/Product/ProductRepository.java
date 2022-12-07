package pl.polsl.shopserver.Product;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.polsl.shopserver.model.entities.dbentity.Product;

public interface ProductRepository extends JpaRepository<Product,Integer> {
}
