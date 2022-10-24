package pl.polsl.shopserver.UserControl;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.polsl.shopserver.dbentity.User;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    @Query(value = "SELECT u.id FROM User u where u.email=:email")
    Integer findUserByEmail(@Param("email")String email);
}
