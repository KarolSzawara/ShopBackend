package pl.polsl.shopserver.Photo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.polsl.shopserver.model.entities.dbentity.Photo;

@Repository
public interface PhotoRepository extends JpaRepository<Photo,Integer> {
}
