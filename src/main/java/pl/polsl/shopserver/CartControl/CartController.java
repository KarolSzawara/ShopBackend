package pl.polsl.shopserver.CartControl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins="http://localhost:4200")
@RequestMapping("/cart")
public class CartController {
    private CartService cartService;
    @Autowired
    CartController(CartService cartService){
        this.cartService=cartService;
    }
    private String addToCart(@RequestHeader("Authorization") String token, @RequestBody CartItem product){
        return "";
    }
}
