package pl.polsl.shopserver.CategoryControl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.polsl.shopserver.model.entities.dbentity.Category;

import java.util.List;
@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;
    @Autowired
    CategoryService(CategoryRepository categoryRepository)
    {
        this.categoryRepository=categoryRepository;
    }
    public ResponseEntity<List<Category>> getAllCategory(){
        return new ResponseEntity<>(categoryRepository.findAll(), HttpStatus.OK);
    }
}
