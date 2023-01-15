package pl.polsl.shopserver.CategoryControl;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.polsl.shopserver.model.entities.dbentity.Category;
import pl.polsl.shopserver.model.entities.dbentity.Photo;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category,Integer> {

}
