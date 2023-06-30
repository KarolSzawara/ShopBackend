package pl.polsl.shopserver.JsonEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


public record LoginDetails(String email,String password) {
}
