package pl.polsl.shopserver.PhotoControl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.polsl.shopserver.dbentity.Photo;

import java.util.List;

@RestController
@CrossOrigin(origins="http://localhost:4200")
@RequestMapping("/photo")
public class PhotoController {

    private final PhotoService photoService;
    @Autowired
    PhotoController(PhotoService photoService){
        this.photoService=photoService;
    }

    @PostMapping("/addPhoto")
    ResponseEntity<Photo> addPhoto(@RequestBody Photo photo){
            return ResponseEntity.ok(photoService.addPhoto(photo));
    }
    @GetMapping
    ResponseEntity<List<Photo>> getPhotos(){
        return ResponseEntity.ok(photoService.getPhotos());
    }
}
