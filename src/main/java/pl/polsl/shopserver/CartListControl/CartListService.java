package pl.polsl.shopserver.CartListControl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.polsl.shopserver.CartControl.CartRepository;
import pl.polsl.shopserver.dbview.Cartlist;
import java.util.List;
@Service
public class CartListService {
    CartListRepository cartListRepository;
    @Autowired
    CartListService(CartListRepository cartListRepository){
        this.cartListRepository=cartListRepository;
    }
    List<Cartlist> getCartByUser(Integer userId){
        return cartListRepository.findCartByUserId(userId);
    }
}
