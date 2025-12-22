package interaction_api.feign.warehouse.model.exception;

public class SpecifiedProductAlreadyInWarehouseException extends Exception {
    public SpecifiedProductAlreadyInWarehouseException(String message){
        super(message);
    }
}
