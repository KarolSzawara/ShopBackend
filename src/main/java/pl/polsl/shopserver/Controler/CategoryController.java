package pl.polsl.shopserver.Controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.polsl.shopserver.CategoryControl.CategoryService;
import pl.polsl.shopserver.JsonEntity.LoginDetails;
import pl.polsl.shopserver.JsonEntity.ReturnToken;
import pl.polsl.shopserver.model.entities.dbentity.Category;

import java.util.List;

@RestController
@CrossOrigin(origins="http://localhost:4200")
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService category){
        this.categoryService=category;
    }

    @GetMapping
    public ResponseEntity<List<Category>> getCategories(){
        return categoryService.getAllCategory();
    }
    @PostMapping("/add")
    ResponseEntity<Category> add(@RequestBody Category category){
        return ResponseEntity.ok(categoryService.addCategory(category));
    }
    @PostMapping("/edit")
    ResponseEntity<Category> edit(@RequestBody Category category){
        return ResponseEntity.ok(categoryService.editCategory(category));
    }
}
