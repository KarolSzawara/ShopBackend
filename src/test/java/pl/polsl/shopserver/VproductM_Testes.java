package pl.polsl.shopserver;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;
import pl.polsl.shopserver.TestRepository.CatRepositoryTest;
import pl.polsl.shopserver.TestRepository.PhotoRepositoryTest;
import pl.polsl.shopserver.TestRepository.ProductRepositoryTest;
import pl.polsl.shopserver.model.entities.dbentity.Category;
import pl.polsl.shopserver.model.entities.dbentity.Photo;
import pl.polsl.shopserver.model.entities.dbentity.Product;
import pl.polsl.shopserver.model.entities.dbview.VproductM;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,classes = ShopserverApplication.class)

public class VproductM_Testes {
    @LocalServerPort
    private int port;

    private String baseUrl="http://localhost";

    private static RestTemplate restTemplate;
    @Autowired
    private CatRepositoryTest catRepository;

    @Autowired
    private ProductRepositoryTest productRepository;


    @Autowired
    private PhotoRepositoryTest photoRepostiory;

    @Before
    public void init(){
        restTemplate=new RestTemplate();
        baseUrl=baseUrl.concat(":").concat(port+"").concat("/product");

    }
    @BeforeEach
    public void setUp(){

    }
    @After
    public void clear(){
        photoRepostiory.deleteAll();
        productRepository.deleteAll();
        catRepository.deleteAll();


    }

    @Test
    public void testVproductGetUrl(){

        Category category=new Category("Categoria",null);
        category=catRepository.save(category);
        Product product=new Product(category,1,"23",100.0,"100","100","100","100","Product","Description","Details");
        product=productRepository.save(product);

        Photo photo = new Photo("/src",product);
        photoRepostiory.save(photo);
        VproductM[] list=restTemplate.getForObject(baseUrl,VproductM[].class);

        Assert.assertEquals(category.getId(),list[0].getIdCategory());
        Assert.assertEquals(photo.getId(),list[0].getIdPhoto());
        Assert.assertEquals(product.getId(),list[0].getIdProduct());

    }


}
