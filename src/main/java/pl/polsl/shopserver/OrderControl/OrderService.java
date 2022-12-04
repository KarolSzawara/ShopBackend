package pl.polsl.shopserver.OrderControl;

import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pl.polsl.shopserver.CartControl.CartRepository;
import pl.polsl.shopserver.Exception.QuantityLimit;
import pl.polsl.shopserver.OrderHistoryControl.OrderItemRepository;
import pl.polsl.shopserver.UserControl.UserService;
import pl.polsl.shopserver.WarehouseController.WarehouseRepository;
import pl.polsl.shopserver.dbentity.*;

import java.time.Instant;
import java.time.LocalDate;
import java.util.LinkedList;
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
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.SERIALIZABLE)
    void purchaseCart(String token){
        User user=userService.getUserByToken(token);

        List<Cart> cartList=cartRepository.findCartByIdUser(user.getId());

        Order order=orderRepostiory.save(new Order(user, Instant.now()));
        for(Cart ite:cartList){
            purchaseProduct(ite,order);
        }
    }
    void purchaseProduct(Cart cartItem, Order order){
        Warehouse warehouse= warehouseRepository.getWarehouseByProductId(cartItem.getIdProd().getId());
        if(warehouse.getQuantityProduct()<cartItem.getOrderItemQuantity()){
            throw  new QuantityLimit("Brak takiej ilość produktu");
        }
        warehouse.setQuantityProduct(warehouse.getQuantityProduct()-cartItem.getOrderItemQuantity());
        warehouseRepository.save(warehouse);
        OrderItem orderItem=new OrderItem(order,cartItem.getOrderItemQuantity(),cartItem.getIdProd().getProductPrize(),cartItem.getIdProd());
        orderItemRepository.save(orderItem);
        cartRepository.delete(cartItem);
    }


}
