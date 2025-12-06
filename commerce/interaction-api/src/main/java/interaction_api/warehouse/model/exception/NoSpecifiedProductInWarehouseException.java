package interaction_api.warehouse.model.exception;

public class NoSpecifiedProductInWarehouseException extends Exception {
    public NoSpecifiedProductInWarehouseException (String message){
        super(message);
    }
}
