package pl.polsl.shopserver.dbview.VproductControll;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.polsl.shopserver.ReadOnlyRepository;
import pl.polsl.shopserver.dbview.VproductM;

public interface VproductMRepository extends ReadOnlyRepository<VproductM,Integer> {
}
