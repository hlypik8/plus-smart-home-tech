package interaction_api.warehouse.model.exception;

public class SpecifiedProductAlreadyInWarehouseException extends Exception {
    public SpecifiedProductAlreadyInWarehouseException(String message){
        super(message);
    }
}
