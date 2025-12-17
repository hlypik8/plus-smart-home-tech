package warehouse;

import interaction_api.cart_feign.model.ShoppingCartDto;
import interaction_api.warehouse_feign.WarehouseFeignClient;
import interaction_api.warehouse_feign.model.AddProductToWarehouseRequest;
import interaction_api.warehouse_feign.model.AddressDto;
import interaction_api.warehouse_feign.model.BookedProductsDto;
import interaction_api.warehouse_feign.model.NewProductInWarehouseRequest;
import interaction_api.warehouse_feign.model.exception.NoSpecifiedProductInWarehouseException;
import interaction_api.warehouse_feign.model.exception.SpecifiedProductAlreadyInWarehouseException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class WarehouseController implements WarehouseFeignClient {

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
