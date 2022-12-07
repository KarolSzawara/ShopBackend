package pl.polsl.shopserver;
import org.junit.Test;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.polsl.shopserver.Exception.EnitityNotFound;
import pl.polsl.shopserver.Exception.EntityAlreadyExist;
import pl.polsl.shopserver.JsonEntity.LoginDetails;
import pl.polsl.shopserver.JsonEntity.ReturnToken;
import pl.polsl.shopserver.User.RegisterProfile;
import pl.polsl.shopserver.User.UserRepository;
import pl.polsl.shopserver.User.UserService;
import pl.polsl.shopserver.model.entities.dbentity.User;

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
        RegisterProfile user=new RegisterProfile("szawra.karol@gmail.com","pass","name","last","ph","1","com","txt","straBe","1","36-3","nrl","213");
        Object object=userService.registerUser(user);
        Assert.assertNotNull(object);
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
        Assert.assertNotNull(returnToken.getJwttoken());
        try{
            userService.loginUser(new LoginDetails("",""));
        }
        catch (EnitityNotFound e){
            assertThat(e)
                    .isInstanceOf(EnitityNotFound.class);
        }

    }
}
