package pl.polsl.shopserver.UserControl;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import pl.polsl.shopserver.dbentity.User;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest()
@RunWith(SpringRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class UserRepositoryTest {
    @Autowired
    UserRepository userRepository;
    @Test
    public void testFindByEmail(){
        User user=new User(1,"mail","pass","name","last","ph","1","com","txt","straBe","1","36-3","nrl","213","phon",null,null,"T",null);
        userRepository.save(user);
        Integer id= userRepository.findUserByEmail("mail");
        Integer idNotExist=userRepository.findUserByEmail("not mail");
        Assert.assertEquals(1,id.intValue());
        Assert.assertNull(idNotExist);



    }
}