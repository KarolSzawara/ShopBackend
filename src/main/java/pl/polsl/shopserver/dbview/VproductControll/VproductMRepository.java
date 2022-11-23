package pl.polsl.shopserver.dbview.VproductControll;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.polsl.shopserver.ReadOnlyRepository;
import pl.polsl.shopserver.dbview.VproductM;
import java.util.List;

public interface VproductMRepository extends ReadOnlyRepository<VproductM,Integer> {
    @Query(value = "SELECT u FROM VproductM u where u.idCategory=:category")
    List<VproductM> findProductByCategory(@Param("category")Integer idCategory);
}
