package pl.polsl.shopserver.ProductControl;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.polsl.shopserver.dbentity.Product;

public interface ProductRepository extends JpaRepository<Product,Integer> {
}
