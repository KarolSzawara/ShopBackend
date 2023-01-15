package pl.polsl.shopserver.CategoryControl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.polsl.shopserver.Exception.EnitityNotFound;
import pl.polsl.shopserver.model.entities.dbentity.Category;

import java.util.List;
import java.util.Optional;

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
    public Category addCategory(Category category){
        return categoryRepository.save(category) ;
    }
    public Category editCategory(Category category){
        Optional<Category> opCategory=categoryRepository.findById(category.getId());
        if(opCategory.isPresent()){
            Category catToChange=opCategory.get();
            catToChange.setCategoryName(category.getCategoryName());
            return categoryRepository.save(catToChange);
        }
        else throw new EnitityNotFound("Brak takiej kategori");
    }
}
