package pl.polsl.shopserver.Controler;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.polsl.shopserver.Vproductv.VproductvService;
import pl.polsl.shopserver.model.entities.dbview.Vproductv;

@RestController
@CrossOrigin(origins="http://localhost:4200")
@RequestMapping(value="details",produces = MediaType.APPLICATION_JSON_VALUE)
public class VproductvController {
    VproductvService vproductvService;
    VproductvController(VproductvService vproductvService){
        this.vproductvService=vproductvService;
    }
    @GetMapping("/{id}")
    public ResponseEntity<Vproductv> getProductDetails(@PathVariable Integer id){
        return ResponseEntity.ok(vproductvService.getProductById(id));
    }
}
