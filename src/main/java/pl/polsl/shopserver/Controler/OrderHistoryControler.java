package pl.polsl.shopserver.Controler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.polsl.shopserver.JsonEntity.OrderDetails;
import pl.polsl.shopserver.OrderItem.FilterType;
import pl.polsl.shopserver.OrderItem.OrderHistoryService;
import pl.polsl.shopserver.Orderhistory;
import pl.polsl.shopserver.model.entities.dbentity.Category;
import pl.polsl.shopserver.model.entities.dbentity.Order;
import pl.polsl.shopserver.model.entities.dbentity.OrderItem;
import pl.polsl.shopserver.model.entities.dbview.Cartlist;

import java.util.List;

@RestController
@CrossOrigin(origins="http://localhost:4200")
@RequestMapping("/history")
public class OrderHistoryControler {
    OrderHistoryService orderHistoryService;
    OrderHistoryControler(OrderHistoryService orderHistoryService){
        this.orderHistoryService=orderHistoryService;
    }
    @GetMapping
    private ResponseEntity<List<Orderhistory>> getOrderHistory(@RequestHeader("Authorization")String token,@RequestParam Integer type){
        return ResponseEntity.ok(orderHistoryService.getOrder(token,FilterType.fromId(type)));
    }
    @GetMapping("/orderitem")
    private ResponseEntity<List<OrderDetails> > getOrderDetails(@RequestParam Integer id){
       return ResponseEntity.ok(orderHistoryService.getOrderDetails(id));
    }
}
