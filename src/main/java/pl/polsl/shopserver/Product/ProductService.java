package pl.polsl.shopserver.Product;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pl.polsl.shopserver.CategoryControl.CategoryRepository;
import pl.polsl.shopserver.Exception.EnitityNotFound;
import pl.polsl.shopserver.Exception.NullValueException;
import pl.polsl.shopserver.JsonEntity.ProductInfo;
import pl.polsl.shopserver.Photo.PhotoRepository;
import pl.polsl.shopserver.Warehouse.WarehouseRepository;
import pl.polsl.shopserver.model.entities.dbentity.Category;
import pl.polsl.shopserver.model.entities.dbentity.Photo;
import pl.polsl.shopserver.model.entities.dbentity.Product;
import pl.polsl.shopserver.model.entities.dbentity.Warehouse;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    ProductRepository productRepository;
    PhotoRepository photoRepository;
    CategoryRepository categoryRepository;
    WarehouseRepository warehouseRepository;
    ProductService(ProductRepository productRepository,PhotoRepository photoRepository,CategoryRepository categoryRepository,WarehouseRepository warehouseRepository){
        this.productRepository=productRepository;
        this.categoryRepository=categoryRepository;
        this.photoRepository=photoRepository;
        this.warehouseRepository=warehouseRepository;
    }

    public List<ProductInfo> getAllProduct(){
        var productInfoArray=new ArrayList<ProductInfo>();
        productRepository.findAll().forEach(product -> {
            ProductInfo pInfo=new ProductInfo();
            productRepository.findCategoryByIdProduct(product.getId())
                    .ifPresentOrElse
                    (
                        cat->pInfo.setCategoryId(cat.getId())
                        ,
                        ()->pInfo.setCategoryId(null)
                    );
            photoRepository.findProductByIdProduct(product.getId())
                    .ifPresent(photo->pInfo.setPhoto(photo));
            warehouseRepository.getWarehouseByProductId(product.getId())
                    .ifPresent(warehouse -> pInfo.setWarehouse(warehouse));
            productInfoArray.add(pInfo);
        });
        return productInfoArray;
    }
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.SERIALIZABLE)
    public boolean addProduct(ProductInfo productInfo){

            productRepository.findAll().forEach(it -> {
                Photo photo=Optional.of(productInfo.getPhoto())
                        .orElseThrow(()->new NullValueException("Brak zdjecia"));
                Product product=Optional.of(productInfo.getProduct())
                        .orElseThrow(()->new NullValueException("Brak danych produktu"));
                product.changeProductNumberByOne();
                categoryRepository.findById(productInfo.getCategoryId())
                        .ifPresentOrElse(category -> product.setIdCategory(category)
                        ,
                        ()->{
                            throw new EnitityNotFound("Brak kategori");
                        });
            Warehouse warehouse=Optional.of(productInfo.getWarehouse()).orElseThrow(()->new NullValueException("Brak danych magazynowych"));
            warehouse.setIdProductWr(product);
            warehouse=warehouseRepository.save(warehouse);
            photo.setIdProduct(product);
            photo=photoRepository.save(photo);
            photo=photoRepository.save(photo);
        });
        return true;

    }
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.SERIALIZABLE)
    public boolean editProduct(ProductInfo productInfo){
        categoryRepository.findById(productInfo.getCategoryId())
                .ifPresent(category -> productInfo.getProduct().setIdCategory(category));
        Product product=productRepository.save(productInfo.getProduct());
        Photo photo=productInfo.getPhoto();
        photo.setIdProduct(product);
        photo=photoRepository.save(photo);
        Warehouse warehouse=productInfo.getWarehouse();
        warehouse.setIdProductWr(product);
        warehouseRepository.save(warehouse);
        return true;
    }
}
