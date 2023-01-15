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
        List<ProductInfo> productInfo=new ArrayList<>();
        List<Product>products=productRepository.findAll();
        for (Product product:products ) {
            Optional<Category> category=productRepository.findCategoryByIdProduct(product.getId());
            Optional<Photo> photo=photoRepository.findProductByIdProduct(product.getId());
            Optional<Warehouse> warehouse=warehouseRepository.getWarehouseByProductId(product.getId());
            ProductInfo pInfo=new ProductInfo();
            pInfo.setProduct(product);
            if(category.isPresent()){
              pInfo.setCategoryId(category.get().getId());
            }
            else{
                pInfo.setCategoryId(null);
            }

            if(photo.isPresent()){
                pInfo.setPhoto(photo.get());
            }
            if(warehouse.isPresent()){
                pInfo.setWarehouse(warehouse.get());
            }

            productInfo.add(pInfo);

        }

        return productInfo;
    }
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.SERIALIZABLE)
    public boolean addProduct(ProductInfo productInfo){
        if(productInfo.getPhoto()==null) throw  new NullValueException("Brak zdjecia");
        Photo photo=productInfo.getPhoto();
        List<Product> products=productRepository.findAll();
        if(productInfo.getProduct()==null) throw  new NullValueException("Brak danych produktu");
        Product product=productInfo.getProduct();
        product.setProductNumber(products.size()+1);
        Optional<Category> opCategory=categoryRepository.findById(productInfo.getCategoryId());
        if(opCategory.isPresent()){
            product.setIdCategory(opCategory.get());
        }
        else throw new EnitityNotFound("Brak kategori");
        product=productRepository.save(product);

        if(productInfo.getWarehouse()==null) throw  new NullValueException("Brak danych magazynowych");
        Warehouse warehouse=productInfo.getWarehouse();
        warehouse.setIdProductWr(product);
        warehouse=warehouseRepository.save(warehouse);
        photo.setIdProduct(product);
        photo=photoRepository.save(photo);
        photo=photoRepository.save(photo);
        return true;

    }
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.SERIALIZABLE)
    public boolean editProduct(ProductInfo productInfo){

        Product product=productInfo.getProduct();
        Optional<Category> opCategory=categoryRepository.findById(productInfo.getCategoryId());
        if(opCategory.isPresent()){
            product.setIdCategory(opCategory.get());
        }
        product=productRepository.save(product);

        Photo photo=productInfo.getPhoto();
        photo.setIdProduct(product);
        photo=photoRepository.save(photo);
        Warehouse warehouse=productInfo.getWarehouse();
        warehouse.setIdProductWr(product);
        warehouseRepository.save(warehouse);
        return true;
    }
}
