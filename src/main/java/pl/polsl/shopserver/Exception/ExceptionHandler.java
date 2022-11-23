package pl.polsl.shopserver.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import pl.polsl.shopserver.dbentity.Photo;

@ControllerAdvice
public class ExceptionHandler {
    @org.springframework.web.bind.annotation.ExceptionHandler(NullValueException.class)
    public ResponseEntity<ExceptionMessage> generteNullException(NullValueException ex){
        ExceptionMessage exceptionMessage=ExceptionMessage.builder()
                .message(ex.getMessage())
                .build();
        return new ResponseEntity<>(exceptionMessage, HttpStatus.BAD_REQUEST);
    }
    @org.springframework.web.bind.annotation.ExceptionHandler(IdExistException.class)
    public ResponseEntity<ExceptionMessage> generteIdExistException(IdExistException ex){
        ExceptionMessage exceptionMessage=ExceptionMessage.builder()
                .message(ex.getMessage())
                .build();
        return new ResponseEntity<>(exceptionMessage, HttpStatus.CONFLICT);
    }
    @org.springframework.web.bind.annotation.ExceptionHandler(ValueOverflowException.class)
    public ResponseEntity<ExceptionMessage> generteValueOverflowException(ValueOverflowException ex){
        ExceptionMessage exceptionMessage=ExceptionMessage.builder()
                .message(ex.getMessage())
                .build();
        return new ResponseEntity<>(exceptionMessage, HttpStatus.LENGTH_REQUIRED);
    }
    @org.springframework.web.bind.annotation.ExceptionHandler(EntityAlreadyExist.class)
    public ResponseEntity<ExceptionMessage> generteEntityAlreadyExist(EntityAlreadyExist ex){
        ExceptionMessage exceptionMessage=ExceptionMessage.builder()
                .message(ex.getMessage())
                .build();
        return new ResponseEntity<>(exceptionMessage, HttpStatus.CONFLICT);
    }
    @org.springframework.web.bind.annotation.ExceptionHandler(EnitityNotFound.class)
    public ResponseEntity<ExceptionMessage> generteEntityNotFound(EnitityNotFound ex){
        ExceptionMessage exceptionMessage=ExceptionMessage.builder()
                .message(ex.getMessage())
                .build();
        return new ResponseEntity<>(exceptionMessage, HttpStatus.NOT_FOUND);
    }
}
