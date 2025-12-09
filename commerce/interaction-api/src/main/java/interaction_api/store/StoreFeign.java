package interaction_api.store;

import interaction_api.store.model.ProductDto;
import interaction_api.store.model.SetProductQuantitySetRequest;
import interaction_api.store.model.enums.ProductCategory;
import interaction_api.store.model.enums.ProductState;
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
    ProductDto update(@RequestBody @Valid ProductDto productDto);

    @PostMapping("/removeProductFromStore")
    Boolean delete(@RequestBody UUID productId);

    @PostMapping("/quantityState")
    Boolean quantityUpdate(@Valid SetProductQuantitySetRequest setProductQuantitySetRequest);

    @GetMapping("/{productId}")
    ProductDto getById(@PathVariable UUID productId);
}
