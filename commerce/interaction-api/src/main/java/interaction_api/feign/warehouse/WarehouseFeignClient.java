package interaction_api.feign.warehouse;

import interaction_api.feign.cart.model.ShoppingCartDto;
import interaction_api.feign.warehouse.model.NewProductInWarehouseRequest;
import interaction_api.feign.warehouse.model.exception.NoSpecifiedProductInWarehouseException;
import interaction_api.feign.warehouse.model.exception.SpecifiedProductAlreadyInWarehouseException;
import interaction_api.feign.warehouse.model.AddProductToWarehouseRequest;
import interaction_api.feign.warehouse.model.AddressDto;
import interaction_api.feign.warehouse.model.BookedProductsDto;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "warehouse")
@RequestMapping("/api/v1/warehouse")
public interface WarehouseFeignClient {

    @PutMapping
    void put(@RequestBody @Valid NewProductInWarehouseRequest newProductInWarehouseRequest) throws SpecifiedProductAlreadyInWarehouseException;

    @PostMapping("/check")
    BookedProductsDto check(@RequestBody @Valid ShoppingCartDto shoppingCartDto) throws NoSpecifiedProductInWarehouseException;

    @PostMapping("/add")
    void add(@RequestBody @Valid AddProductToWarehouseRequest addProductToWarehouseRequest) throws NoSpecifiedProductInWarehouseException;

    @GetMapping("/address")
    AddressDto get();
}
