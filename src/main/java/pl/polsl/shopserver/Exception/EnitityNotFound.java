package pl.polsl.shopserver.Exception;

public class EnitityNotFound extends RuntimeException{
    public EnitityNotFound(String message){
        super(message);
    }
}
