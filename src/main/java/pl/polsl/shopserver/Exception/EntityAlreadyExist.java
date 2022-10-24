package pl.polsl.shopserver.Exception;

public class EntityAlreadyExist extends RuntimeException {
    public EntityAlreadyExist(String messege){
        super(messege);
    }
}
