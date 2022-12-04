package pl.polsl.shopserver.WarehouseController;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.polsl.shopserver.dbentity.Cart;
import pl.polsl.shopserver.dbentity.Warehouse;

import java.util.List;

@Repository
public interface WarehouseRepository extends JpaRepository<Warehouse,Integer> {
    @Query(value = "SELECT w FROM Warehouse w where w.idProductWr.id=:productId")
    Warehouse getWarehouseByProductId(@Param("productId")Integer productId);
}
