package pl.polsl.shopserver;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import pl.polsl.shopserver.Photo.PhotoRepository;
import pl.polsl.shopserver.Photo.PhotoService;
import pl.polsl.shopserver.Product.ProductRepository;
import pl.polsl.shopserver.model.entities.dbentity.Photo;
import pl.polsl.shopserver.model.entities.dbentity.Product;

import java.util.LinkedList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,classes = ShopserverApplication.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class PhotoControllerTest {
    @LocalServerPort
    private int port;

    private static RestTemplate restTemplate;

    @Autowired
    PhotoService photoService;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    PhotoRepository photoRepository;
    @BeforeEach
    public void init(){
        restTemplate=new RestTemplate();
        baseUrl=baseUrl.concat(":").concat(port+"").concat("/photo/addPhoto");

    }
    @AfterEach
    public void clear(){
        photoRepository.deleteAll();
    }

    private String baseUrl="http://localhost";
    @Test
    void addPhoto() {
        //given
        Product product=new Product(null,1,"23",100.0,"100","100","100","100","Product","Description","Details");
        product=productRepository.save(product);
        Photo photo=new Photo(1,"text",product);
        Photo photo1=new Photo(3,"sssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss",null);
        photoRepository.save(photo);

        List<HttpClientErrorException> errorList=new LinkedList<>();
        HttpEntity<Photo> httpId=new HttpEntity<>(photo);
        HttpEntity<Photo> httpOverflow=new HttpEntity<>(photo1);
        HttpEntity<Photo> httpNull=new HttpEntity<>(new Photo());
        //when
        try {
            ResponseEntity<Photo> photeRes = restTemplate.postForEntity(baseUrl, httpId, Photo.class);
        }catch (HttpClientErrorException e){
            errorList.add(e);
        };
        try {
            ResponseEntity<Photo> photeRes = restTemplate.postForEntity(baseUrl, httpOverflow, Photo.class);
        }catch (HttpClientErrorException e){
            errorList.add(e);
        };
        try {
            ResponseEntity<Photo> photeRes = restTemplate.postForEntity(baseUrl, httpNull, Photo.class);
        }catch (HttpClientErrorException e){
            errorList.add(e);
        };



        Assert.assertEquals(HttpStatus.CONFLICT,errorList.get(0).getStatusCode());
        Assert.assertEquals(HttpStatus.LENGTH_REQUIRED, errorList.get(1).getStatusCode());
        Assert.assertEquals(HttpStatus.BAD_REQUEST, errorList.get(2).getStatusCode());
    }
}