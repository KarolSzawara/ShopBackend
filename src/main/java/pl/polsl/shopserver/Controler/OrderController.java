package pl.polsl.shopserver.Controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.polsl.shopserver.JsonEntity.LoginDetails;
import pl.polsl.shopserver.JsonEntity.ReturnToken;
import pl.polsl.shopserver.Order.OrderService;

@RestController
@CrossOrigin(origins="http://localhost:4200")
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;
    @Autowired
    OrderController(OrderService orderService){
        this.orderService=orderService;
    }

}
