package pl.polsl.shopserver.OrderItem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.polsl.shopserver.Auth.JwtToken;
import pl.polsl.shopserver.Order.OrderHistoryRepository;
import pl.polsl.shopserver.Order.OrderRepostiory;
import pl.polsl.shopserver.Orderhistory;
import pl.polsl.shopserver.User.UserRepository;
import pl.polsl.shopserver.model.entities.dbentity.Order;

import java.util.List;
@Service
public class OrderHistoryService {
    OrderHistoryRepository orderRepostiory;
    UserRepository userRepository;
    @Autowired
    OrderHistoryService(OrderHistoryRepository orderRepostiory, UserRepository userRepository){
        this.orderRepostiory=orderRepostiory;
        this.userRepository=userRepository;
    }
    public List<Orderhistory> getOrder(String token){
        String email= JwtToken.validateToke(token);
        Integer idUser=userRepository.findUserByEmail(email);
        return orderRepostiory.findOrderByUserId(idUser);
    }
}
