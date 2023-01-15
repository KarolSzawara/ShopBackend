package pl.polsl.shopserver.Controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.polsl.shopserver.JsonEntity.ProductInfo;
import pl.polsl.shopserver.Product.ProductService;
import pl.polsl.shopserver.model.entities.dbentity.Category;
import pl.polsl.shopserver.model.entities.dbentity.Product;

import java.util.List;

@RestController
@CrossOrigin(origins="http://localhost:4200")
@RequestMapping("/admin")
public class AdminController {

    ProductService productService;
    @Autowired
    AdminController(ProductService productService){
        this.productService=productService;
    }
    @GetMapping
    public ResponseEntity<List<ProductInfo>> getCategories(){
        return ResponseEntity.ok(productService.getAllProduct());
    }
    @PostMapping("/add")
    ResponseEntity<Boolean> add(@RequestBody ProductInfo productInfo){
        return ResponseEntity.ok(productService.addProduct(productInfo));
    }
    @PostMapping("/edit")
    ResponseEntity<Boolean> edit(@RequestBody ProductInfo productInfo){
        return ResponseEntity.ok(productService.editProduct(productInfo));
    }
}
