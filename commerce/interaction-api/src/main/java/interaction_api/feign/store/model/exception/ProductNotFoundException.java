package interaction_api.feign.store.model.exception;

public class ProductNotFoundException extends Exception {

    public ProductNotFoundException(String message){
        super(message);
    }
}
