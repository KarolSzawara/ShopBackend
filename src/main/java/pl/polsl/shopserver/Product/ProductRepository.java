package pl.polsl.shopserver.Product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.polsl.shopserver.model.entities.dbentity.Category;
import pl.polsl.shopserver.model.entities.dbentity.Photo;
import pl.polsl.shopserver.model.entities.dbentity.Product;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product,Integer> {
    @Query(value = "SELECT p.idCategory FROM Product  p WHERE p.id=:idProduct")
    Optional<Category> findCategoryByIdProduct(@Param("idProduct")Integer idProduct);
}
