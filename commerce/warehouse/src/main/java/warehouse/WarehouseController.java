package warehouse;

import interaction_api.warehouse.WarehouseFeign;
import interaction_api.warehouse.model.AddProductToWarehouseRequest;
import interaction_api.warehouse.model.exception.NoSpecifiedProductInWarehouseException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/warehouse")
@RequiredArgsConstructor
public class WarehouseController implements WarehouseFeign {

    private final WarehouseService service;

    @Override
    public void add(AddProductToWarehouseRequest addProductToWarehouseRequest)
            throws NoSpecifiedProductInWarehouseException {
        service.add(addProductToWarehouseRequest);
    }
}
