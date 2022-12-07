package pl.polsl.shopserver.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.polsl.shopserver.model.entities.dbentity.User;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    @Query(value = "SELECT u.id FROM User u where u.email=:email")
    Integer findUserByEmail(@Param("email")String email);

    @Query(value = "SELECT u.id FROM User u where u.verficationToken=:token")
    Integer findUserByVerficationToken(@Param("token")String token);
}
