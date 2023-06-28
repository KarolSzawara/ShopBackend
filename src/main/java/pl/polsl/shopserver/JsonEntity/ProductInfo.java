package pl.polsl.shopserver.JsonEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.polsl.shopserver.model.entities.dbentity.Category;
import pl.polsl.shopserver.model.entities.dbentity.Photo;
import pl.polsl.shopserver.model.entities.dbentity.Product;
import pl.polsl.shopserver.model.entities.dbentity.Warehouse;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor

public record ProductInfo(
        Product product,
        Integer categoryId,
        Photo photo,
        Warehouse warehouse
) {


}
