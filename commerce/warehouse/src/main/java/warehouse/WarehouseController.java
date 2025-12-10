package warehouse;

import interaction_api.cart.model.ShoppingCartDto;
import interaction_api.warehouse.WarehouseFeign;
import interaction_api.warehouse.model.AddProductToWarehouseRequest;
import interaction_api.warehouse.model.AddressDto;
import interaction_api.warehouse.model.BookedProductsDto;
import interaction_api.warehouse.model.NewProductInWarehouseRequest;
import interaction_api.warehouse.model.exception.NoSpecifiedProductInWarehouseException;
import interaction_api.warehouse.model.exception.SpecifiedProductAlreadyInWarehouseException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/warehouse")
@RequiredArgsConstructor
public class WarehouseController implements WarehouseFeign {

    private final WarehouseService service;

    @Override
    public void put(NewProductInWarehouseRequest newProductInWarehouseRequest) throws SpecifiedProductAlreadyInWarehouseException {
        service.putNewProduct(newProductInWarehouseRequest);
    }

    @Override
    public BookedProductsDto check(ShoppingCartDto shoppingCartDto) throws NoSpecifiedProductInWarehouseException {
       return service.check(shoppingCartDto);
    }

    @Override
    public void add(AddProductToWarehouseRequest addProductToWarehouseRequest)
            throws NoSpecifiedProductInWarehouseException {
        service.addProduct(addProductToWarehouseRequest);
    }

    @Override
    public AddressDto get(){
        return service.getAddress();
    }
}
