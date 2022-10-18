package pl.polsl.shopserver.TestRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.polsl.shopserver.dbentity.Photo;

public interface PhotoRepositoryTest extends JpaRepository<Photo,Integer> {
}
