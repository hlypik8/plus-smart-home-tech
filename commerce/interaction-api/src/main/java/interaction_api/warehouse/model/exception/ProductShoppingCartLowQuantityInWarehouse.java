package interaction_api.warehouse.model.exception;

public class ProductShoppingCartLowQuantityInWarehouse extends Exception {
    public ProductShoppingCartLowQuantityInWarehouse(String message){
        super(message);
    }
}
