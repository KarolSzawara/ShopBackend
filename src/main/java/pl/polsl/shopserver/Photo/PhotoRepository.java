package pl.polsl.shopserver.Photo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.polsl.shopserver.model.entities.dbentity.Photo;
import pl.polsl.shopserver.model.entities.dbview.VproductM;

import java.util.List;
import java.util.Optional;

@Repository
public interface PhotoRepository extends JpaRepository<Photo,Integer> {
    @Query(value = "SELECT p FROM Photo  p WHERE p.idProduct.id=:idProduct")
    Optional<Photo> findProductByIdProduct(@Param("idProduct")Integer idProduct);
}
