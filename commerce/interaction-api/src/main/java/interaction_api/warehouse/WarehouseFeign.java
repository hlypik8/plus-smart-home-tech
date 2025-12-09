package interaction_api.warehouse;

import interaction_api.cart.model.ShoppingCartDto;
import interaction_api.warehouse.model.AddProductToWarehouseRequest;
import interaction_api.warehouse.model.AddressDto;
import interaction_api.warehouse.model.BookedProductsDto;
import interaction_api.warehouse.model.NewProductInWarehouseRequest;
import interaction_api.warehouse.model.exception.NoSpecifiedProductInWarehouseException;
import interaction_api.warehouse.model.exception.SpecifiedProductAlreadyInWarehouseException;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "warehouse", path = "/api/v1/warehouse")
public interface WarehouseFeign {

    @PutMapping
    void put(@RequestBody @Valid NewProductInWarehouseRequest newProductInWarehouseRequest) throws SpecifiedProductAlreadyInWarehouseException;

    @PostMapping("/check")
    BookedProductsDto check(@RequestBody @Valid ShoppingCartDto shoppingCartDto) throws NoSpecifiedProductInWarehouseException;

    @PostMapping("/add")
    void add(@RequestBody @Valid AddProductToWarehouseRequest addProductToWarehouseRequest) throws NoSpecifiedProductInWarehouseException;

    @GetMapping("/address")
    AddressDto get();
}
