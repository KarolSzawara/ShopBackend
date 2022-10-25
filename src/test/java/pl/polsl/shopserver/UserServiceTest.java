package pl.polsl.shopserver;
import org.junit.Test;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import pl.polsl.shopserver.Exception.EnitityNotFound;
import pl.polsl.shopserver.Exception.IdExistException;
import pl.polsl.shopserver.Exception.EntityAlreadyExist;
import pl.polsl.shopserver.Exception.ValueOverflowException;
import pl.polsl.shopserver.JsonEntity.LoginDetails;
import pl.polsl.shopserver.JsonEntity.ReturnToken;
import pl.polsl.shopserver.UserControl.UserRepository;
import pl.polsl.shopserver.UserControl.UserService;
import pl.polsl.shopserver.dbentity.User;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest()
@RunWith(SpringRunner.class)
public class UserServiceTest {
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;
    @Test
    public void RegisterTest(){
        User user=new User(1,"szawra.karol@gmail.com","pass","name","last","ph","1","com","txt","straBe","1","36-3","nrl","213","phon",null,null,"N",null);
        Object object=userService.registerUser(user);
        Assert.assertNotNull(object);
        user.setId(2);
        try {
            userService.registerUser(user);
        }
        catch (EntityAlreadyExist e){
            assertThat(e)
                    .isInstanceOf(EntityAlreadyExist.class);
        }

    }
    @Test
    public void LoginUser(){
        User user=new User(1,"mail","pass","name","last","ph","1","com","txt","straBe","1","36-3","nrl","213","phon",null,null,"T",null);
        userRepository.save(user);
        ReturnToken returnToken=userService.loginUser(new LoginDetails(user.getEmail(),user.getPassword()));
        Assert.assertNotNull(returnToken.getToken());
        try{
            userService.loginUser(new LoginDetails("",""));
        }
        catch (EnitityNotFound e){
            assertThat(e)
                    .isInstanceOf(EnitityNotFound.class);
        }

    }
}
