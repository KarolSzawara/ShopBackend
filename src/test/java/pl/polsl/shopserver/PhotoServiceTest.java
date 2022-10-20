package pl.polsl.shopserver;

import org.junit.After;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.server.ResponseStatusException;
import pl.polsl.shopserver.Exception.IdExistException;
import pl.polsl.shopserver.Exception.NullValueException;
import pl.polsl.shopserver.Exception.ValueOverflowException;
import pl.polsl.shopserver.PhotoControl.PhotoRepository;
import pl.polsl.shopserver.PhotoControl.PhotoService;
import pl.polsl.shopserver.dbentity.Photo;
import pl.polsl.shopserver.dbentity.Product;

import java.util.ArrayList;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;

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

    @AfterEach
    public void clear(){
        photoRepository.deleteAll();
    }
    @Test
    void addPhotoSrcToLong() {

        //given
        Photo photo1=new Photo(1,"ssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss",null);
        //when
        try {
        Photo saveValue=photoService.addPhoto(photo1);
        //then
        exception.expectMessage("Błąd długości ścieżki pliku");
        }catch (Exception e){
            assertThat(e)
                    .isInstanceOf(ValueOverflowException.class);
        }
    }
    @Test()
    void addPhotoIdExist()throws Exception{
        try {
            //given
            Photo photo = new Photo(1, "src", null);
            Photo photo2 = new Photo(1, "src2",null);
            //when
            photoService.addPhoto(photo);
            Photo response = photoService.addPhoto(photo2);
            //then
        }catch (Exception e){
            assertThat(e)
                    .isInstanceOf(IdExistException.class);
        }
    }
    @Test
    void addPhoto(){
        try {
        //given
        Photo photo=new Photo();
        //when
        Photo response=photoService.addPhoto(photo);
        //then
        Assert.assertEquals(response.getSrcPhoto(),photo.getSrcPhoto());
        }catch (Exception e){
            assertThat(e)
                    .isInstanceOf(NullValueException.class);
        }
    }


}