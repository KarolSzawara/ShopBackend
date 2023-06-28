package pl.polsl.shopserver.JsonEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public record ReturnRegisterResponse(String user,String messege) {

}
