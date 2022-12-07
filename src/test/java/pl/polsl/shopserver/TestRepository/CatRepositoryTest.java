package pl.polsl.shopserver.TestRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.polsl.shopserver.model.entities.dbentity.Category;

public interface CatRepositoryTest extends JpaRepository<Category,Integer> {
}
