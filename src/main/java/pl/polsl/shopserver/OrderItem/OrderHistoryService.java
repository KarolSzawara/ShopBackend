package pl.polsl.shopserver.OrderItem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.polsl.shopserver.Auth.JwtToken;
import pl.polsl.shopserver.JsonEntity.OrderDetails;
import pl.polsl.shopserver.Order.OrderHistoryRepository;
import pl.polsl.shopserver.Order.OrderRepostiory;
import pl.polsl.shopserver.Orderhistory;
import pl.polsl.shopserver.Product.ProductRepository;
import pl.polsl.shopserver.User.UserRepository;
import pl.polsl.shopserver.model.entities.dbentity.Order;
import pl.polsl.shopserver.model.entities.dbentity.OrderItem;
import pl.polsl.shopserver.model.entities.dbentity.Product;

import java.time.*;
import java.time.temporal.TemporalUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderHistoryService {
    OrderHistoryRepository orderRepostiory;
    UserRepository userRepository;
    OrderItemRepository orderItemRepository;
    ProductRepository productRepository;
    @Autowired
    OrderHistoryService(OrderHistoryRepository orderRepostiory, UserRepository userRepository,OrderItemRepository orderItemRepository,ProductRepository productRepository){
        this.orderRepostiory=orderRepostiory;
        this.userRepository=userRepository;
        this.orderItemRepository=orderItemRepository ;
        this.productRepository=productRepository;
    }
    public List<Orderhistory> getOrder(String token,Integer type){
        String email= JwtToken.validateToke(token);
        Integer idUser=userRepository.findUserByEmail(email);
        List<Orderhistory> orderHistories=orderRepostiory.findOrderByUserId(idUser);
        List<Orderhistory> resHistories=new ArrayList<>();
        LocalDateTime localDateTime = LocalDateTime.now();
        LocalDateTime minus6Months = localDateTime.minusMonths(6);
        LocalDateTime year = localDateTime.minusYears(1);
        LocalDateTime twoYear = localDateTime.minusYears(2);
        ZoneId zoneId = ZoneId.of("UTC");
        Instant sixMonthsAgo = minus6Months.atZone(zoneId).toInstant();
        Instant oneYearAgo=year.atZone(zoneId).toInstant();
        Instant twoYearAgo=twoYear.atZone(zoneId).toInstant();
        if(type==0)return orderHistories;
        for(Orderhistory order:orderHistories){
            if(type==1){
                if (order.getOrderDate().isAfter(sixMonthsAgo)){
                    resHistories.add(order);
                }
            }
            if(type==2){
                if (order.getOrderDate().isAfter(oneYearAgo)){
                    resHistories.add(order);
                }
            }
            if(type==3){
                if (order.getOrderDate().isAfter(twoYearAgo)){
                    resHistories.add(order);
                }
            }

        }

        return resHistories;
    }
    public List<OrderDetails>  getOrderDetails(Integer id){
        List<OrderItem> orderItems= orderItemRepository.findCartByIdUser(id);
        List<OrderDetails> orderDetails=new ArrayList<>();
        for(OrderItem item:orderItems){
            Optional<Product> product=productRepository.findById(item.getProduct().getId());
            if(product.isPresent()){
                orderDetails.add(new OrderDetails(product.get().getProductName(),item));
            }
        }
        return orderDetails;
    }
}
