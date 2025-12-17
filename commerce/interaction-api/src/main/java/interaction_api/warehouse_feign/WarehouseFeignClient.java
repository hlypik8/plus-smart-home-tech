package interaction_api.warehouse_feign;

import interaction_api.cart_feign.model.ShoppingCartDto;
import interaction_api.warehouse_feign.model.AddProductToWarehouseRequest;
import interaction_api.warehouse_feign.model.AddressDto;
import interaction_api.warehouse_feign.model.BookedProductsDto;
import interaction_api.warehouse_feign.model.NewProductInWarehouseRequest;
import interaction_api.warehouse_feign.model.exception.NoSpecifiedProductInWarehouseException;
import interaction_api.warehouse_feign.model.exception.SpecifiedProductAlreadyInWarehouseException;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "warehouse", path = "/api/v1/warehouse")
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
