package pl.polsl.shopserver.Photo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.polsl.shopserver.Exception.EnitityNotFound;
import pl.polsl.shopserver.Exception.IdExistException;
import pl.polsl.shopserver.Exception.NullValueException;
import pl.polsl.shopserver.Exception.ValueOverflowException;
import pl.polsl.shopserver.model.entities.dbentity.Photo;

import java.util.List;
import java.util.Optional;

@Service
public class PhotoService  {
    private final PhotoRepository photoRepository;
    @Autowired
    public PhotoService(PhotoRepository photoRepository){
        this.photoRepository=photoRepository;
    }

    public List<Photo> getPhotos(){
        return photoRepository.findAll();
    }

    public Photo addPhoto(Photo photo)
    {
        if(photo.getSrcPhoto()==null){
            throw new NullValueException("Brak ściażki zdjecia");
        }
        if(photo.getSrcPhoto().length()>100){
            throw new ValueOverflowException("Przepełniona wartość cieżki");
        }
        if(photoRepository.findById(photo.getId()).isPresent()){
            throw new IdExistException("Id zdjecia już istnieje");
        }
        return photoRepository.save(photo);
    }

    public Photo getPhotosById(Integer id){
        return photoRepository.findById(id).orElseThrow( throw  new EnitityNotFound("Nie znaleziono zmienej"));
    }
}

