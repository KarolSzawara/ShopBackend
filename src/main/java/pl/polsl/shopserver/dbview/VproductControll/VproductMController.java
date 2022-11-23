package pl.polsl.shopserver.dbview.VproductControll;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.polsl.shopserver.CategoryControl.CategoryService;
import pl.polsl.shopserver.dbview.VproductM;

import java.awt.*;
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
}
