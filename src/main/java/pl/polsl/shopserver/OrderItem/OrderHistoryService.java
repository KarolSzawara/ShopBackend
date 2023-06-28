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
    public List<Orderhistory> getOrder(String token,FilterType type){
        String email= JwtToken.validateToke(token);
        Integer idUser=userRepository.findUserByEmail(email);
        var orderHistories=orderRepostiory.findOrderByUserId(idUser);
        var resHistories=new ArrayList<Orderhistory>();
        LocalDateTime localDateTime = LocalDateTime.now();
        ZoneId zoneId = ZoneId.of("UTC");

        for(Orderhistory order:orderHistories){
            switch (type){
                case Without -> {return orderHistories;}
                case SixMonth -> {
                    LocalDateTime minus6Months = localDateTime.minusMonths(6);
                    Instant sixMonthsAgo = minus6Months.atZone(zoneId).toInstant();
                    if (order.getOrderDate().isAfter(sixMonthsAgo)){
                        resHistories.add(order);
                    }
                }
                case OneYear -> {
                    LocalDateTime year = localDateTime.minusYears(1);
                    Instant oneYearAgo=year.atZone(zoneId).toInstant();
                    if (order.getOrderDate().isAfter(oneYearAgo)){
                        resHistories.add(order);
                    }
                }
                case TwoYear -> {
                    LocalDateTime twoYear = localDateTime.minusYears(2);
                    Instant twoYearAgo=twoYear.atZone(zoneId).toInstant();
                    if (order.getOrderDate().isAfter(twoYearAgo)){
                        resHistories.add(order);
                    }
                }
            }
        }

        return resHistories;
    }
    public List<OrderDetails>  getOrderDetails(Integer id){
        List<OrderDetails> orderDetails=new ArrayList<>();
        orderItemRepository.findCartByIdUser(id).forEach(orderItem ->
        {
            productRepository.findById(orderItem.getProduct().getId())
                    .ifPresent(product -> orderDetails.add(new OrderDetails(product.getProductName(),orderItem)));
        });
        return orderDetails;
    }
}
