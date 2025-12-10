package interaction_api.store;

import interaction_api.store.model.ProductDto;
import interaction_api.store.model.SetProductQuantitySetRequest;
import interaction_api.store.model.enums.ProductCategory;
import interaction_api.store.model.enums.ProductState;
import interaction_api.store.model.exception.ProductNotFoundException;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;


@FeignClient(name = "shopping-store", path = "api/v1/shopping-store")
public interface StoreFeign {

    @GetMapping
    Page<ProductDto> getByCategory(@RequestParam ProductCategory productCategory, Pageable pageable);

    @PutMapping
    ProductDto add(@RequestBody @Valid ProductDto productDto);

    @PostMapping
    ProductDto update(@RequestBody @Valid ProductDto productDto) throws ProductNotFoundException;

    @PostMapping("/removeProductFromStore")
    Boolean delete(@RequestBody UUID productId) throws ProductNotFoundException;

    @PostMapping("/quantityState")
    Boolean quantityUpdate(@Valid SetProductQuantitySetRequest setProductQuantitySetRequest) throws ProductNotFoundException;

    @GetMapping("/{productId}")
    ProductDto getById(@PathVariable UUID productId) throws ProductNotFoundException;
}
