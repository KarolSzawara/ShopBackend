package pl.polsl.shopserver.Exception;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ExceptionMessage {
    String message;
}
