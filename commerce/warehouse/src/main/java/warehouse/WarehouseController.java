package warehouse;

import interaction_api.feign.cart.model.ShoppingCartDto;
import interaction_api.feign.warehouse.WarehouseFeignClient;
import interaction_api.feign.warehouse.model.AddProductToWarehouseRequest;
import interaction_api.feign.warehouse.model.AddressDto;
import interaction_api.feign.warehouse.model.BookedProductsDto;
import interaction_api.feign.warehouse.model.NewProductInWarehouseRequest;
import interaction_api.feign.warehouse.model.exception.NoSpecifiedProductInWarehouseException;
import interaction_api.feign.warehouse.model.exception.SpecifiedProductAlreadyInWarehouseException;
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
