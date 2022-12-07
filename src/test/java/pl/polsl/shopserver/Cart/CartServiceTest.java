package pl.polsl.shopserver.Cart;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.polsl.shopserver.Auth.JwtToken;
import pl.polsl.shopserver.Exception.AuthorizationFailed;
import pl.polsl.shopserver.Exception.QuantityLimit;
import pl.polsl.shopserver.Product.ProductRepository;
import pl.polsl.shopserver.User.UserRepository;
import pl.polsl.shopserver.Warehouse.WarehouseRepository;
import pl.polsl.shopserver.model.entities.dbentity.Product;
import pl.polsl.shopserver.model.entities.dbentity.User;
import pl.polsl.shopserver.model.entities.dbentity.Warehouse;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest()
@RunWith(SpringRunner.class)
class CartServiceTest {

    @Autowired
    CartRepository cartRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ProductRepository productRepository;

    @Autowired
    WarehouseRepository warehouseRepository;

    CartService cartService;

    String token;



    @BeforeEach
    void setUp() {
        cartService=new CartService(cartRepository,userRepository,productRepository,warehouseRepository);
        prepareData();
    }

    @AfterEach
    void tearDown() {
        cartRepository.deleteAll();
    }

    @Test
    void addToCart() {
        CartItem cartItem=new CartItem(1,2);
        cartService.addToCart(token,cartItem);
        Assert.assertEquals(cartRepository.findAll().get(0).getId().intValue(),1);
        try{
            cartService.addToCart("",cartItem);
        }catch (Exception e){
            assertThat(e)
                    .isInstanceOf(AuthorizationFailed.class);
        }
        try{
            cartItem.setProductQuantity(4000);
            cartService.addToCart(token,
                      cartItem);
        }catch (Exception e){
            assertThat(e)
                    .isInstanceOf(QuantityLimit.class);
        }
        EditCart editCart=new EditCart(1,5);
        cartService.editCart(token,editCart);
        assertEquals( cartRepository.findAll().get(0).getOrderItemQuantity(),5);
        editCart.setProductQuantity(50);
        try{

            cartService.editCart(token,editCart);
        }catch (Exception e){
            assertThat(e)
                    .isInstanceOf(QuantityLimit.class);
        }
        cartItem.setProductQuantity(2);
        cartService.addToCart(token,cartItem);
        assertEquals( cartRepository.findAll().get(0).getOrderItemQuantity(),7);
        cartService.deleteCartItem(token,editCart);
        assertNotNull(cartRepository.findAll());
    }




    void prepareData(){
        Product product=productRepository.save(new Product(null,1,"23",100.0,"100","100","100","100","Product","Description","Details"));
        User user=userRepository.save(new User(1,"mail","pass","name","last","ph","1","com","txt","straBe","1","36-3","nrl","213","phon",null,null,"T",null));
        token=JwtToken.creatToken(user);
        warehouseRepository.save(new Warehouse(1,product,30,40.2));
    }
}