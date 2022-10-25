package pl.polsl.shopserver.UserControl;

import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.polsl.shopserver.Auth.ConfirmationLink;
import pl.polsl.shopserver.Auth.JwtToken;
import pl.polsl.shopserver.Exception.EnitityNotFound;
import pl.polsl.shopserver.Exception.EntityAlreadyExist;
import pl.polsl.shopserver.JsonEntity.ReturnRegisterResponse;
import pl.polsl.shopserver.JsonEntity.ReturnToken;
import pl.polsl.shopserver.JsonEntity.LoginDetails;
import pl.polsl.shopserver.Services.EmailService;
import pl.polsl.shopserver.dbentity.User;

import javax.persistence.EntityNotFoundException;
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
        String verficationLink=ConfirmationLink.createLink();
        user.setVerficationToken(verficationLink);
        userRepository.save(user);
        emailService.sendEmail(user.getEmail(),"Weryfikacja e-maila","http://localhost:4200/verfication?="+verficationLink);
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
    public String confirmEmail(String token){
        Integer userId=userRepository.findUserByVerficationToken(token);
        if(userId!=null){
            throw new EntityNotFoundException("Nie znaleziona użytkownika");
        }
        Optional<User> optionalUser=userRepository.findById(userId);
        User user;
        if(optionalUser.isPresent()){
            user=optionalUser.get();
            if(user.getEnable().equals('T')){
                throw new EntityAlreadyExist("Użytkownik został już zwerfikowany");
            }
            else {
                user.setEnable("T");
                userRepository.save(user);
            }

        }
        else throw new EntityNotFoundException("Nie znaleziona użytkownika");
        return "Not found";
    }
}
