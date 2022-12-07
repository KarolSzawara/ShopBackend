package pl.polsl.shopserver.Vproductv;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import pl.polsl.shopserver.model.entities.dbview.Vproductv;
@Transactional(readOnly = true)
public interface VproductvRepository extends JpaRepository<Vproductv,Integer> {
}
