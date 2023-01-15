package pl.polsl.shopserver.Warehouse;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.polsl.shopserver.model.entities.dbentity.Warehouse;

import java.util.Optional;

@Repository
public interface WarehouseRepository extends JpaRepository<Warehouse,Integer> {
    @Query(value = "SELECT w FROM Warehouse w where w.idProductWr.id=:productId")
    Optional<Warehouse> getWarehouseByProductId(@Param("productId")Integer productId);
}
