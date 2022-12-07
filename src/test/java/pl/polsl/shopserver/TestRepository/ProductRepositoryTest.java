package pl.polsl.shopserver.TestRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.polsl.shopserver.model.entities.dbentity.Product;

public interface ProductRepositoryTest extends JpaRepository<Product,Integer> {
}
