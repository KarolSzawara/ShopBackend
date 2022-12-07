package pl.polsl.shopserver.Vproductv;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.polsl.shopserver.Exception.EnitityNotFound;
import pl.polsl.shopserver.model.entities.dbview.Vproductv;

import java.util.Optional;

@Service
public class VproductvService {
    VproductvRepository vproductvRepository;
    @Autowired
    VproductvService(VproductvRepository vproductvRepository){
        this.vproductvRepository=vproductvRepository;
    }
   public Vproductv getProductById(Integer id){
        Optional<Vproductv> product=vproductvRepository.findById(id);
        if(product.isPresent()){
            return product.get();
        }
        else throw new EnitityNotFound("Nieznaleziono takiego productu");
    }
}
