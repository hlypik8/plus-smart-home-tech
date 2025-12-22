package interaction_api.feign.cart.model.exception;

public class NoProductsInShoppingCartException extends Exception {
    public NoProductsInShoppingCartException(String message) {
        super(message);
    }
}