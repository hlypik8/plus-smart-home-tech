package interaction_api.warehouse;

import interaction_api.cart.model.ShoppingCartDto;
import interaction_api.warehouse.model.AddProductToWarehouseRequest;
import interaction_api.warehouse.model.AddressDto;
import interaction_api.warehouse.model.BookedProductsDto;
import interaction_api.warehouse.model.NewProductInWarehouseRequest;
import interaction_api.warehouse.model.exception.NoSpecifiedProductInWarehouseException;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(name = "warehouse", path = "/api/v1/warehouse")
public interface WarehouseFeign {

    @PutMapping
    void put(NewProductInWarehouseRequest newProductInWarehouseRequest);

    @PostMapping("/check")
    BookedProductsDto check(ShoppingCartDto shoppingCartDto);

    @PostMapping("/add")
    void add(AddProductToWarehouseRequest addProductToWarehouseRequest) throws NoSpecifiedProductInWarehouseException;

    @GetMapping("/address")
    AddressDto get();
}
