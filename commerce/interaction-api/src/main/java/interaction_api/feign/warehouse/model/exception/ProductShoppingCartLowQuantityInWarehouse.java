package interaction_api.feign.warehouse.model.exception;

public class ProductShoppingCartLowQuantityInWarehouse extends Exception {
    public ProductShoppingCartLowQuantityInWarehouse(String message){
        super(message);
    }
}
