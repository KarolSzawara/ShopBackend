package pl.polsl.shopserver.UserControl;

import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.polsl.shopserver.Auth.JwtToken;
import pl.polsl.shopserver.Exception.EnitityNotFound;
import pl.polsl.shopserver.Exception.EntityAlreadyExist;
import pl.polsl.shopserver.JsonEntity.ReturnRegisterResponse;
import pl.polsl.shopserver.JsonEntity.ReturnToken;
import pl.polsl.shopserver.JsonEntity.LoginDetails;
import pl.polsl.shopserver.Services.EmailService;
import pl.polsl.shopserver.dbentity.User;

import java.util.Optional;

@Service
public class UserService {
    UserRepository userRepository;
    EmailService emailService;
    @Autowired
    UserService(UserRepository userRepository,EmailService emailService){
        this.userRepository=userRepository;
        this.emailService=emailService;
    }

    public ReturnRegisterResponse registerUser(User user)
    {

        Integer userId=userRepository.findUserByEmail(user.getEmail());
        if(userId!=null){
            throw new EntityAlreadyExist("Użytkownik z tym mailem istnieje");
        }
        userRepository.save(user);
        String randomCode = RandomString.make(64);
        emailService.sendEmail("szawra.karol@gmail.com","verfication",randomCode);
        return new ReturnRegisterResponse(user.getEmail(),"Użytkownik został stworzony");
    }
    public ReturnToken loginUser(LoginDetails loginDetails){
        Integer userId=userRepository.findUserByEmail(loginDetails.getEmail());
        if(userId!=null){
           Optional<User> user =userRepository.findById(userId);
           if(user.isPresent())
           {
               if(user.get().getPassword().equals(loginDetails.getPassword()))
               {
                    return new ReturnToken(user.get().getEmail(),JwtToken.creatToken(user.get()));
               }
           }
        }
        throw new EnitityNotFound("Nie poprawny login lub hasło");
    }
}
