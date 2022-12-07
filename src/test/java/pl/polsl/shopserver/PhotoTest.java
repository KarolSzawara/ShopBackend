package pl.polsl.shopserver;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pl.polsl.shopserver.Photo.PhotoService;
import pl.polsl.shopserver.model.entities.dbentity.Photo;
import pl.polsl.shopserver.model.entities.dbentity.Product;

import java.util.LinkedList;
import java.util.List;


import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PhotoTest {
    @Test
    public void getPhotos_tests(){

        //given
        PhotoService photoService=mock(PhotoService.class);
        when(photoService.getPhotos()).thenReturn(preparePhotos());
        //when
        List<Photo> photoList=preparePhotos();
        //then
        Assert.assertThat(photoList, Matchers.hasSize(2));
    }
    private List<Photo> preparePhotos(){
        List<Photo> photoList=new LinkedList<>();
        photoList.add(new Photo("url",new Product()));
        photoList.add(new Photo("url1",new Product()));
        return photoList;
    }
    @Test
    public void addPhoto_tests(){

        //given
        PhotoService photoService=mock(PhotoService.class);
        given(photoService.addPhoto(Mockito.any(Photo.class))).willReturn(new Photo("url",new Product()));
        //when

        Photo photo= photoService.addPhoto(new Photo("url1",new Product()));
        //then
        Assert.assertEquals(photo.getSrcPhoto(),"url");
    }



}
