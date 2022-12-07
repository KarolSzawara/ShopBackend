package pl.polsl.shopserver.CartList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.polsl.shopserver.Auth.JwtToken;
import pl.polsl.shopserver.User.UserRepository;
import pl.polsl.shopserver.model.entities.dbview.Cartlist;
import java.util.List;
@Service
public class CartListService {
    CartListRepository cartListRepository;
    UserRepository userRepository;
    @Autowired
    CartListService(CartListRepository cartListRepository,UserRepository userRepository){
        this.cartListRepository=cartListRepository;
        this.userRepository=userRepository;
    }
    public List<Cartlist> getCartByUser(String token){
        String email=JwtToken.validateToke(token);
        Integer userId=userRepository.findUserByEmail(email);
        return cartListRepository.findCartByUserId(userId);
    }
}
