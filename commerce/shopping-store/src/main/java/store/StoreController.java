package store;

import interaction_api.store_feign.StoreFeignClient;
import interaction_api.store_feign.model.ProductDto;
import interaction_api.store_feign.model.SetProductQuantitySetRequest;
import interaction_api.store_feign.model.enums.ProductCategory;
import interaction_api.store_feign.model.exception.ProductNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class StoreController implements StoreFeignClient {

    private final StoreService service;

    @Override
    public Page<ProductDto> getByCategory(ProductCategory category, Pageable pageable) {
        return service.getByCategory(category, pageable);
    }

    @Override
    public ProductDto add(ProductDto dto) {
        return service.add(dto);
    }

    @Override
    public ProductDto update(ProductDto dto) throws ProductNotFoundException {
        return service.update(dto);
    }

    @Override
    public Boolean delete(UUID productId) throws ProductNotFoundException {
        return service.remove(productId);
    }

    @Override
    public Boolean quantityUpdate(SetProductQuantitySetRequest request) throws ProductNotFoundException {
        return service.quantityUpdate(request);
    }

    @Override
    public ProductDto getById(UUID productId) throws ProductNotFoundException {
        return service.getById(productId);
    }
}