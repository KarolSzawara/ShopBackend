package pl.polsl.shopserver;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.web.client.RestTemplate;
import pl.polsl.shopserver.TestRepository.CatRepositoryTest;
import pl.polsl.shopserver.TestRepository.PhotoRepositoryTest;
import pl.polsl.shopserver.TestRepository.ProductRepositoryTest;
import pl.polsl.shopserver.dbentity.Category;
import pl.polsl.shopserver.dbentity.Photo;
import pl.polsl.shopserver.dbentity.Product;
import pl.polsl.shopserver.dbview.VproductM;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductMSTest {

        @LocalServerPort
        private int port;

        private String baseUrl="http://localhost";

        private static RestTemplate restTemplate;

        @Autowired
        private CatRepositoryTest catRepository;

        private ProductRepositoryTest productRepository;

        private PhotoRepositoryTest photoRepostiory;

        @BeforeAll
        public static void init(){
            restTemplate=new RestTemplate();
        }
        @BeforeEach
        public void setUp(){
            baseUrl=baseUrl.concat(":").concat(port+"").concat("/product");
        }

        @Test
        public void testVproduct(){

            Category category=new Category("Categoria",null);
            category=catRepository.save(category);
            Product product=new Product(category,1,"23","100","100","100","100","100","Product","Description","Details");
            product=productRepository.save(product);
            Photo photo = new Photo("/src",product);
            photoRepostiory.save(photo);
            VproductM[] list=restTemplate.getForObject(baseUrl,VproductM[].class);

            Assert.assertEquals(category.getId(),list[0].getIdCategory());
            Assert.assertEquals(photo.getId(),list[0].getIdPhoto());
            Assert.assertEquals(product.getId(),list[0].getIdProduct());

        }


    }


