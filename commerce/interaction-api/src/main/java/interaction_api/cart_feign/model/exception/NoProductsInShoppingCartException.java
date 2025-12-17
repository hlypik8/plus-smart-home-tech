package interaction_api.cart_feign.model.exception;

public class NoProductsInShoppingCartException extends Exception {
    public NoProductsInShoppingCartException(String message) {
        super(message);
    }
}