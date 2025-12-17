package interaction_api.warehouse_feign.model.exception;

public class ProductShoppingCartLowQuantityInWarehouse extends Exception {
    public ProductShoppingCartLowQuantityInWarehouse(String message){
        super(message);
    }
}
