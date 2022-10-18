package pl.polsl.shopserver;

import ch.qos.logback.core.encoder.EchoEncoder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import pl.polsl.shopserver.Exception.ExceptionsIdExist;
import pl.polsl.shopserver.PhotoControl.PhotoRepository;
import pl.polsl.shopserver.PhotoControl.PhotoService;
import pl.polsl.shopserver.dbentity.Photo;
import pl.polsl.shopserver.dbentity.Product;

import java.util.ArrayList;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
@SpringBootTest()
@RunWith(SpringRunner.class)
class PhotoServiceTest {



    @Autowired
    PhotoRepository photoRepository;

    @Rule
    public final ExpectedException exception = ExpectedException.none();


    PhotoService photoService;

    @BeforeEach
    public void init(){
        photoService=new PhotoService(photoRepository);
    }
    @Test
    void addPhotoSrcToLong() {
        //given
        Photo photo=new Photo("src",new Product());
        Photo photo1=new Photo("ssssssssssssssssssssssssssssssssssssssssssssssssss",new Product());
        //when
        Photo saveValue=photoService.addPhoto(photo1);
        //then
        exception.expectMessage("Błąd długości ścieżki pliku");
    }
    @Test()
    void addPhotoIdExist()throws Exception{
        try {
            //given
            Photo photo = new Photo(1, "src", new Product());
            Photo photo2 = new Photo(1, "src2", new Product());
            //when
            photoService.addPhoto(photo);
            Photo response = photoService.addPhoto(photo2);
            //then
        }catch (Exception e){
            assertThat(e)
                    .isInstanceOf(ExceptionsIdExist.class);
        }
    }
    @Test
    void addPhoto(){
        //given
        Photo photo=new Photo("src",new Product());
        //when
        Photo response=photoService.addPhoto(photo);
        //then
        Assert.assertEquals(response.getSrcPhoto(),photo.getSrcPhoto());
    }

    @Test
    void getPhotosById() {
    }
    public ArrayList<Photo> prepareReturnValue(){
        ArrayList<Photo> photoList=new ArrayList<>();
        photoList.add(new Photo(1,"src",new Product()));
        photoList.add(new Photo(2,"ssssssssssssssssssssssss",new Product()));
        return photoList;
    }
}