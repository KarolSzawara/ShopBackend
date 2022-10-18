package pl.polsl.shopserver.TestRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.polsl.shopserver.dbentity.Product;
import pl.polsl.shopserver.dbview.VproductM;

public interface ProductRepositoryTest extends JpaRepository<Product,Integer> {
}
