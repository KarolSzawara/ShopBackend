package pl.polsl.shopserver.dbview.VproductControll;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.polsl.shopserver.dbview.VproductM;

import java.util.List;

@Service
public class VproductMService {
    private final VproductMRepository vproductMRepository;
    @Autowired
    VproductMService(VproductMRepository vproductMRepository){
        this.vproductMRepository=vproductMRepository;
    }

    public ResponseEntity<List<VproductM>> getAll(){
        return new ResponseEntity<>(vproductMRepository.findAll(),HttpStatus.OK);
    }

    public List<VproductM> getProductByCategory(Integer category){
        if(category==0||category==null) return vproductMRepository.findAll();
        return vproductMRepository.findProductByCategory(category);
    }

}
