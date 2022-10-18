package pl.polsl.shopserver.PhotoControl;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.polsl.shopserver.dbentity.Photo;

@Repository
public interface PhotoRepository extends JpaRepository<Photo,Integer> {
}
