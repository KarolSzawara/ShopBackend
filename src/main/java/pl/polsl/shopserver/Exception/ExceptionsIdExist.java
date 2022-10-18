package pl.polsl.shopserver.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;
@ResponseStatus(value = HttpStatus.CONFLICT,reason = "Id już istnieje")
public class ExceptionsIdExist extends RuntimeException {
    public ExceptionsIdExist(String messege) {
        super(messege);
    }
}
