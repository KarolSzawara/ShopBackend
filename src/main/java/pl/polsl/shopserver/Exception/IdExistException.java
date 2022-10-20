package pl.polsl.shopserver.Exception;

public class IdExistException extends RuntimeException {
    public IdExistException(String messege) {
        super(messege);
    }
}
