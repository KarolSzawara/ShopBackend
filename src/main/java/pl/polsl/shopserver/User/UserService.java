package pl.polsl.shopserver.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.polsl.shopserver.Auth.ConfirmationLink;
import pl.polsl.shopserver.Auth.JwtToken;
import pl.polsl.shopserver.Auth.RefreshToken;
import pl.polsl.shopserver.Exception.EnitityNotFound;
import pl.polsl.shopserver.Exception.EntityAlreadyExist;
import pl.polsl.shopserver.JsonEntity.ReturnRegisterResponse;
import pl.polsl.shopserver.JsonEntity.ReturnToken;
import pl.polsl.shopserver.JsonEntity.LoginDetails;
import pl.polsl.shopserver.Services.EmailService;
import pl.polsl.shopserver.model.entities.dbentity.User;

import javax.persistence.EntityNotFoundException;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserService {
    private UserRepository userRepository;
    EmailService emailService;
    @Autowired
    UserService(UserRepository userRepository,EmailService emailService){
        this.userRepository=userRepository;
        this.emailService=emailService;
    }

    public ReturnRegisterResponse registerUser(RegisterProfile user)
    {

        Integer userId=userRepository.findUserByEmail(user.getEmail());
        if(userId!=null){
            throw new EntityAlreadyExist("Użytkownik z tym mailem istnieje");
        }
        User newUser=User.builder()
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .flatNumber(user.getFlatNumber())
                .phonNumber(user.getPhonNumber())
                .companyOrPerson(user.getCompanyOrPerson())
                .companyName(user.getCompanyName())
                .nip(user.getNip())
                .street(user.getStreet())
                .streetNumber(user.getStreetNumber())
                .postCode(user.getPostCode())
                .postTown(user.getPostTown())
                .password(user.getPassword())
                .enable("F")
                .build();
        userRepository.save(newUser);
        var verficationLink=ConfirmationLink.createLink();
        newUser.setVerficationToken(verficationLink);
        userRepository.save(newUser);
        emailService.sendEmail(user.getEmail(),"Weryfikacja e-maila","http://localhost:4200/verfication/"+verficationLink);
        return new ReturnRegisterResponse(user.getEmail(),"Uzytkownik został stworzony");
    }
    public ReturnToken loginUser(LoginDetails loginDetails){
        Integer userId=userRepository.findUserByEmail(loginDetails.getEmail());
        if(userId==null) {
            throw  new EnitityNotFound("Taki użytkownik nie istnieje");
        }
        else {
            return userRepository.findById(userId).map(user -> {
                if (user.getPassword().equals(loginDetails.getPassword())) {
                    if (user.getEnable().equals("A")) {
                        return new ReturnToken(user.getEmail(), "admin", "admin");
                    }
                    if (user.getEnable().equals("T")) {
                        String refreshToken = RefreshToken.creatToken(user);
                        user.setRefreshToken(refreshToken);
                        userRepository.save(user);
                        return new ReturnToken(user.getEmail(), JwtToken.creatToken(user), refreshToken);
                    } else {
                        throw new EnitityNotFound("Email nie potwierdzony");
                    }
                } else throw new EnitityNotFound("Nie poprawny login lub haslo");
            }).orElseThrow(() -> new EnitityNotFound("Taki użytkownik nie istnieje"));
        }

    }
    public String confirmEmail(String token){
        Integer userId=userRepository.findUserByVerficationToken(token);
        if(userId==null){
            throw new EntityNotFoundException("Nie znaleziona uzytkownika");
        }
        userRepository.findById(userId).ifPresentOrElse(user -> {
            if(user.getEnable().equals("T")){
                throw new EntityAlreadyExist("Użytkownik został już zwerfikowany");
            }
            else {
                user.setEnable("T");
                userRepository.save(user);
            }
        }
        ,
        ()->  {
            throw new EntityNotFoundException("Nie znaleziona uzytkownika");
        });
        return "Zatwierdzono poprawnie";
    }
    public User getUserByToken(String token){
        String email = JwtToken.validateToke(token);
        Integer id=userRepository.findUserByEmail(email);
        return userRepository.findById(id).orElseThrow(
                ()->new EnitityNotFound("Użytkownik nie istnieje")
        );

    }
}
