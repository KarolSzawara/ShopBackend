package pl.polsl.shopserver.CartListControl;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.polsl.shopserver.dbview.Cartlist;
import java.util.List;
public interface CartListRepository extends JpaRepository<Cartlist,Integer> {
    @Query(value = "SELECT c FROM Cartlist c where c.idUse=:iduser")
    List<Cartlist> findCartByUserId(@Param("iduser")Integer iduser);
}
