package pl.polsl.shopserver.Order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.polsl.shopserver.Orderhistory;
import pl.polsl.shopserver.model.entities.dbentity.Order;

import java.util.List;

public interface OrderHistoryRepository extends JpaRepository<Orderhistory,Integer> {
    @Query(value = "SELECT o FROM Orderhistory o where o.idUser=:iduser")
    List<Orderhistory> findOrderByUserId(@Param("iduser")Integer iduser);
}
