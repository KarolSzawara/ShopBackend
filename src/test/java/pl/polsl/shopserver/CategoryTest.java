package pl.polsl.shopserver;

import org.junit.After;
import org.junit.Assert;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.web.client.RestTemplate;
import pl.polsl.shopserver.TestRepository.CatRepositoryTest;
import pl.polsl.shopserver.model.entities.dbentity.Category;

import java.util.HashSet;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CategoryTest {
    @LocalServerPort
    private int port;

    private String baseUrl="http://localhost";

    private static RestTemplate restTemplate;

    @Autowired
    private CatRepositoryTest h2Repository;

    @BeforeAll
    public static void init(){
        restTemplate=new RestTemplate();
    }
    @BeforeEach
    public void setUp(){
        baseUrl=baseUrl.concat(":").concat(port+"").concat("/categories");
    }
    @After
    public void deleteAll(){
        h2Repository.deleteAll();
    }
    @Test
    public void testCategories(){
        h2Repository.save(new Category(1,"Categoria",new HashSet<>()));
        h2Repository.save(new Category("Opał",new HashSet<>()));
        Category[] list=restTemplate.getForObject(baseUrl,Category[].class);

        Assert.assertEquals(list[0].getCategoryName(),h2Repository.findAll().get(0).getCategoryName());
        Assert.assertEquals(list.length,h2Repository.findAll().size());
    }
}
