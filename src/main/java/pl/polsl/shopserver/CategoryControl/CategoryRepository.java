package pl.polsl.shopserver.CategoryControl;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.polsl.shopserver.model.entities.dbentity.Category;

public interface CategoryRepository extends JpaRepository<Category,Integer> {
}
