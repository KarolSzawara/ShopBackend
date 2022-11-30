package pl.polsl.shopserver.CartControl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.polsl.shopserver.CartListControl.CartListService;
import pl.polsl.shopserver.dbview.Cartlist;
import java.util.List;
@RestController
@CrossOrigin(origins="http://localhost:4200")
@RequestMapping("/cart")
public class CartController {
    private CartService cartService;
    private CartListService cartListService;
    @Autowired
    CartController(CartService cartService,CartListService cartListService){
        this.cartListService=cartListService;
        this.cartService=cartService;
    }
    @PostMapping("/add")
    private ResponseEntity<String> addToCart(@RequestHeader("Authorization") String token, @RequestBody CartItem product){
        if(cartService.addToCart(token,product)){
            return ResponseEntity.ok("Product został dodany");
        }
        return ResponseEntity.badRequest().body("Bład podczas dodawania");
    }
    @GetMapping
    private ResponseEntity<List<Cartlist>> getCart(@RequestHeader("Authorization")String token){
            return ResponseEntity.ok(cartListService.getCartByUser(token));
    }

}
