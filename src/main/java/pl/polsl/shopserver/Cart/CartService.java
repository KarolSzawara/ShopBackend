package pl.polsl.shopserver.Cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.polsl.shopserver.Auth.JwtToken;
import pl.polsl.shopserver.Exception.EnitityNotFound;
import pl.polsl.shopserver.Exception.NullValueException;
import pl.polsl.shopserver.Exception.QuantityLimit;
import pl.polsl.shopserver.Product.ProductRepository;
import pl.polsl.shopserver.User.UserRepository;
import pl.polsl.shopserver.Warehouse.WarehouseRepository;
import pl.polsl.shopserver.model.entities.dbentity.Cart;
import pl.polsl.shopserver.model.entities.dbentity.Product;
import pl.polsl.shopserver.model.entities.dbentity.User;
import pl.polsl.shopserver.model.entities.dbentity.Warehouse;

import java.util.Optional;

@Service
public class CartService {
    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final WarehouseRepository warehouseRepository;
    @Autowired
    CartService(CartRepository cartRepository,UserRepository userRepository,ProductRepository productRepository,WarehouseRepository warehouseRepository){
        this.cartRepository=cartRepository;
        this.userRepository=userRepository;
        this.productRepository=productRepository;
        this.warehouseRepository=warehouseRepository;
    }

    public boolean addToCart(String token,CartItem cartItem){
        User user=new User();
        Product product=new Product();
        Cart cart;
        if(cartItem.productQuantity<=0){
            throw new NullValueException("Błędna ilość");
        }
        String email=JwtToken.validateToke(token);
        Integer userId=userRepository.findUserByEmail(email);
        Optional<User> userOptional=userRepository.findById(userId);
        if(userOptional.isPresent()){
            user=userOptional.get();
        }
        else throw new EnitityNotFound("Uzytkownik nie istnieje");

        Optional<Product> productOptional=productRepository.findById(cartItem.productID);
        if(productOptional.isPresent())product=productOptional.get();
        else throw new EnitityNotFound("Niepoprawny produkt");
        Optional<Warehouse> warehouseOptional=warehouseRepository.findById(product.getId());
        if(warehouseOptional.get().getQuantityProduct()<cartItem.productQuantity)throw new QuantityLimit("Niema takiej ilości produktu");
        Integer cartId=cartRepository.findCartByUserIdAndProductId(userId, cartItem.getProductID());
        if(cartId!=null){
            Optional<Cart> cartOptional=cartRepository.findById(cartId);
            if(cartOptional.isPresent()){
                cart=cartOptional.get();
                cart.setOrderItemQuantity(cart.getOrderItemQuantity()+cartItem.getProductQuantity());
            }
            else{
                throw new EnitityNotFound("Błąd podczas wczytywaniu encji");
            }
        }
        else{
            cart=new Cart(cartItem.productQuantity,user,product);
        }


        cartRepository.save(cart);
        return true;
    }

    public boolean editCart(String token, EditCart editCart){
       Optional<Cart> cartOptional= cartRepository.findById(editCart.cartID);
       if(cartOptional.isPresent()){

           Cart cart=cartOptional.get();
           Optional<Warehouse> warehouseOptional=warehouseRepository.findById(cart.getIdProd().getId());
           if(warehouseOptional.get().getQuantityProduct()<editCart.getProductQuantity())throw new QuantityLimit("Niema takiej ilości produktu");
           cart.setOrderItemQuantity(editCart.productQuantity);
           cartRepository.save(cart);
       }
        return true;

    }
    public boolean  deleteCartItem(String token,EditCart editCart){
        Optional<Cart> cartOptional= cartRepository.findById(editCart.cartID);
        if(cartOptional.isPresent()) {
            cartRepository.delete(cartOptional.get());
        }
        else throw new EnitityNotFound("Nie znaleziono produktu");
        return true;
        }

    public boolean  editCartItem(EditCart editCart){
        Optional<Cart> cartOptional= cartRepository.findById(editCart.cartID);
        if(cartOptional.isPresent()) {
            Cart cart=cartOptional.get();
            cart.setOrderItemQuantity(editCart.productQuantity);
            cartRepository.save(cart);
        }
        else throw new EnitityNotFound("Nie znaleziono produktu");
        return true;
    }
}


