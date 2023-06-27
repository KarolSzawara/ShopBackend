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

        Cart cart;
        if(cartItem.productQuantity<=0){
            throw new NullValueException("Błędna ilość");
        }
        String email=JwtToken.validateToke(token);

        Integer userId=userRepository.findUserByEmail(email);

        User user=userRepository.findById(userId)
                .orElseThrow(()->{
                    throw new EnitityNotFound("Uzytkownik nie istnieje");
                });
        var product=productRepository.findById(cartItem.productID)
                .orElseThrow(()->{
                    throw new EnitityNotFound("Niepoprawny produkt");
                });

        var warehouse=warehouseRepository.findById(product.getId()).orElseThrow(
                ()->{
                    throw new EnitityNotFound("Nie znaleziono takiego magazynu");
                }
        );
        if(warehouse.getQuantityProduct()<cartItem.productQuantity)throw new QuantityLimit("Niema takiej ilości produktu");

        Integer cartId=cartRepository.findCartByUserIdAndProductId(userId, cartItem.getProductID());
        if(cartId!=null){
            cart=cartRepository.findById(cartId).orElseThrow(()-> {
                throw new EnitityNotFound("Błąd podczas wczytywaniu encji");
            });
            cart.setOrderItemQuantity(cart.getOrderItemQuantity()+cartItem.getProductQuantity());

        }
        else{
            cart=new Cart(cartItem.productQuantity,user,product);
        }
        cartRepository.save(cart);
        return true;
    }

    public boolean editCart(String token, EditCart editCart){
       cartRepository.findById(editCart.cartID).ifPresent(
               it->{
                   warehouseRepository.findById(it.getIdProd().getId()).ifPresentOrElse(
                           warehouse -> {
                               if(warehouse.getQuantityProduct()<editCart.getProductQuantity())
                                   throw new QuantityLimit("Niema takiej ilości produktu");

                               it.setOrderItemQuantity(editCart.getProductQuantity());
                               cartRepository.save(it);
                           },
                           ()->{
                                throw new EnitityNotFound("Nieznaleziono koszyka do edycji");
                           }
                   );
               }
       );
        return true;
    }
    public boolean  deleteCartItem(String token,EditCart editCart){
        cartRepository.findById(editCart.cartID).ifPresentOrElse(it->{
            cartRepository.delete(it);
        },()->{
            throw new EnitityNotFound("Nie znaleziono produktu");
        });
        return true;
        }

    public boolean  editCartItem(EditCart editCart){
        cartRepository.findById(editCart.cartID).ifPresentOrElse(cart->{
            cart.setOrderItemQuantity(editCart.getProductQuantity());
            cartRepository.save(cart);
        },()->{
            throw new EnitityNotFound("Nie znaleziono produktu");
        });
        return true;
    }
}


