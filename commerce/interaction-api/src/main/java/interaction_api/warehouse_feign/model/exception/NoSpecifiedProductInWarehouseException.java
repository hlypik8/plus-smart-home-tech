package interaction_api.warehouse_feign.model.exception;

public class NoSpecifiedProductInWarehouseException extends Exception {
    public NoSpecifiedProductInWarehouseException (String message){
        super(message);
    }
}
