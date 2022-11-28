package pl.polsl.shopserver.CartControl;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.polsl.shopserver.dbentity.Cart;

public interface CartRepository extends JpaRepository<Cart,Integer> {
    @Query(value = "SELECT c.id FROM Cart c where c.idUse.id=:userId and c.idProd.id=:productId")
    Integer findCartByUserIdAndProductId(@Param("userId")Integer userId,@Param("productId")Integer productId);
}
