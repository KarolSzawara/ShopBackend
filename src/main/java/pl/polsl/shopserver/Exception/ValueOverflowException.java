package pl.polsl.shopserver.Exception;

public class ValueOverflowException extends RuntimeException{
    public ValueOverflowException(String message){
        super(message);
    }
}
