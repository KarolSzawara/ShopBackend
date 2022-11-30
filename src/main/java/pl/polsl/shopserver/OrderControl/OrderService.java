package pl.polsl.shopserver.OrderControl;

import org.springframework.beans.factory.annotation.Autowired;
import pl.polsl.shopserver.Auth.JwtToken;
import pl.polsl.shopserver.CartControl.CartRepository;
import pl.polsl.shopserver.CartControl.CartService;
import pl.polsl.shopserver.OrderHistoryControl.OrderItemRepository;
import pl.polsl.shopserver.UserControl.UserRepository;
import pl.polsl.shopserver.UserControl.UserService;
import pl.polsl.shopserver.WarehouseController.WarehouseRepository;
import pl.polsl.shopserver.dbentity.Cart;
import pl.polsl.shopserver.dbentity.User;

import java.util.Optional;
import java.util.List;
public class OrderService {
    OrderRepostiory orderRepostiory;
    OrderItemRepository orderItemRepository;
    CartRepository cartRepository;
    WarehouseRepository warehouseRepository;
    UserService userService;
    @Autowired
    OrderService(OrderRepostiory orderRepostiory, OrderItemRepository orderItemRepository,CartRepository cartRepository,WarehouseRepository warehouseRepository,UserService userService){
        this.cartRepository=cartRepository;
        this.orderItemRepository=orderItemRepository;
        this.orderRepostiory=orderRepostiory;
        this.warehouseRepository=warehouseRepository;
        this.userService=userService;
    }
    boolean purchaseCart(String token){
        User user=userService.getUserByToken(token);
        List<Cart> cartList=cartRepository.findCartByIdUser(user.getId());
    }


}
