package pl.polsl.shopserver.JsonEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


public record ReturnToken(String email,
        String Jwttoken,
        String RefreshToken) {

}
