package pl.polsl.shopserver.Controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.polsl.shopserver.Cart.CartItem;
import pl.polsl.shopserver.Cart.CartService;
import pl.polsl.shopserver.Cart.EditCart;
import pl.polsl.shopserver.Cart.Response;
import pl.polsl.shopserver.CartList.CartListService;
import pl.polsl.shopserver.Order.OrderService;
import pl.polsl.shopserver.model.entities.dbview.Cartlist;
import java.util.List;
@RestController
@CrossOrigin(origins="http://localhost:4200")
@RequestMapping("/cart")
public class CartController {
    private CartService cartService;
    private CartListService cartListService;
    private final OrderService orderService;
    @Autowired
    CartController(CartService cartService, CartListService cartListService, OrderService orderService){
        this.cartListService=cartListService;
        this.cartService=cartService;
        this.orderService=orderService;
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
    @PostMapping("/buy")
    ResponseEntity purchaseCart(@RequestHeader("Authorization")String token,@RequestBody String text){
        orderService.purchaseCart(token);
        return ResponseEntity.ok().build();
    }
    @PostMapping("/delete")
    ResponseEntity deleteFromCart(@RequestHeader("Authorization")String token, @RequestBody Cartlist editCart){
        cartService.deleteCartItem(token,new EditCart(editCart.getIdCart(),editCart.getOrderItemQuantity()));
        return ResponseEntity.ok().build();
    }
    @PostMapping("/edit")
    ResponseEntity editFromCart(@RequestBody Cartlist editCart){
        cartService.editCartItem(new EditCart(editCart.getIdCart(),editCart.getOrderItemQuantity()));
        return ResponseEntity.ok().build();
    }
}
