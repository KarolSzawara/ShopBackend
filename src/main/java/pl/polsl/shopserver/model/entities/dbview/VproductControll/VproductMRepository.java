package pl.polsl.shopserver.model.entities.dbview.VproductControll;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import pl.polsl.shopserver.ReadOnlyRepository;
import pl.polsl.shopserver.model.entities.dbentity.Product;
import pl.polsl.shopserver.model.entities.dbview.VproductM;

import java.util.List;
@Transactional(readOnly = true)
public interface VproductMRepository extends ReadOnlyRepository<VproductM,Integer> {
    @Query(value = "SELECT u FROM VproductM u where u.idCategory=:category")
    List<VproductM> findProductByCategory(@Param("category")Integer idCategory);

    @Query(value = "SELECT u FROM VproductM u where u.productName like :name")
    List<VproductM> findByProductName(@Param("name") String name);
}
