package interaction_api.feign.cart.model.exception;

public class NotAuthorizedUserException extends Exception {
    public NotAuthorizedUserException(String message) {
        super(message);
    }
}