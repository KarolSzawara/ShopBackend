package pl.polsl.shopserver.Controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.polsl.shopserver.model.entities.dbview.VproductControll.VproductMService;
import pl.polsl.shopserver.model.entities.dbview.VproductM;

import java.util.List;

@RestController
@CrossOrigin(origins="http://localhost:4200")
@RequestMapping("/product")
public class VproductMController {
    private final VproductMService vproductMService;
    @Autowired
    public VproductMController(VproductMService vproductMService){
        this.vproductMService=vproductMService;
    }


    @GetMapping
    public ResponseEntity<List<VproductM>> getProduct(){
        return vproductMService.getAll();
    }

    @GetMapping("/{category}")
    public ResponseEntity<List<VproductM>> getProductByCategory(@PathVariable Integer category){
        return ResponseEntity.ok(vproductMService.getProductByCategory(category));
    }
    @GetMapping("/search")
    public ResponseEntity<List<VproductM>> search(@RequestParam String query){
        return new ResponseEntity<>(vproductMService.searchProduct(query), HttpStatus.OK);
    }
}
