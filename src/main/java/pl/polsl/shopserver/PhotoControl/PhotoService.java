package pl.polsl.shopserver.PhotoControl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import pl.polsl.shopserver.Exception.IdExistException;
import pl.polsl.shopserver.Exception.NullValueException;
import pl.polsl.shopserver.Exception.ValueOverflowException;
import pl.polsl.shopserver.dbentity.Photo;

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

    public ResponseEntity<Photo> getPhotosById(Integer id){
        Optional<Photo> photo=photoRepository.findById(id);
        return photoRepository.findById(id).isPresent()
                ? new ResponseEntity<Photo>(photo.get(),HttpStatus.OK)
                : new ResponseEntity<Photo>(HttpStatus.NOT_FOUND);
    }
}

