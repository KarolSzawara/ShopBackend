package pl.polsl.shopserver.Controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.polsl.shopserver.Cart.CartItem;
import pl.polsl.shopserver.Cart.CartService;
import pl.polsl.shopserver.Cart.Response;
import pl.polsl.shopserver.CartList.CartListService;
import pl.polsl.shopserver.model.entities.dbview.Cartlist;
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

    @GetMapping
    private ResponseEntity<List<Cartlist>> getCart(@RequestHeader("Authorization")String token){
            return ResponseEntity.ok(cartListService.getCartByUser(token));
    }
    @PostMapping("/add")
    private ResponseEntity<Response> addToCart(@RequestHeader("Authorization") String token, @RequestBody CartItem product){
        if(cartService.addToCart(token,product)){
            return ResponseEntity.ok(new Response("Product zostal dodany"));
        }
        return ResponseEntity.badRequest().body(new Response("Blad podczas dodawania"));
    }

}
