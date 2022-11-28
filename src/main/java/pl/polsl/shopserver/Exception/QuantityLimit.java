package pl.polsl.shopserver.Exception;

public class QuantityLimit extends RuntimeException{
    public QuantityLimit(String message){
        super(message);
    }
}
