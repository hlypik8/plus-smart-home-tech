package interaction_api.cart.model.exception;

public class NoProductsInShoppingCartException extends Exception {
    public NoProductsInShoppingCartException(String message) {
        super(message);
    }
}