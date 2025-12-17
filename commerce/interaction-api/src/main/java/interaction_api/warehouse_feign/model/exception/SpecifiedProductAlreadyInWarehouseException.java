package interaction_api.warehouse_feign.model.exception;

public class SpecifiedProductAlreadyInWarehouseException extends Exception {
    public SpecifiedProductAlreadyInWarehouseException(String message){
        super(message);
    }
}
