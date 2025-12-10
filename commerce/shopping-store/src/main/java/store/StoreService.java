package store;

import interaction_api.store.model.ProductDto;
import interaction_api.store.model.SetProductQuantitySetRequest;
import interaction_api.store.model.enums.ProductCategory;
import interaction_api.store.model.enums.ProductState;
import interaction_api.store.model.exception.ProductNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class StoreService {

    private final StoreRepository repository;
    private final StoreMapper mapper;

    @Transactional(readOnly = true)
    public Page<ProductDto> getByCategory(ProductCategory category, Pageable pageable) {

        log.info("Запрос товаров в категории {} с параметрами страницы {}", category.toString(), pageable.toString());

        Page<StoreProduct> product = repository.findAllByProductCategory(category, pageable);

        log.info("Запрос товаров в категории {} выполнен успешно", category.toString());
        log.debug("Получено товаров {}", product.getTotalElements());

        return product.map((mapper::toProductDto));
    }

    public ProductDto add(ProductDto productDto) {

        log.info("Запрос на добавление нового товара {}", productDto.getProductName());

        StoreProduct product = mapper.toStoreProduct(productDto);
        StoreProduct savedProduct = repository.save(product);

        log.info("Запрос на добавление выполнен успешно");
        log.debug("Добавлен новый продукт id: {}", savedProduct.getProductId());

        return mapper.toProductDto(savedProduct);
    }

    public ProductDto update(ProductDto productDto) throws ProductNotFoundException {

        log.info("Запрос на обновление товара с id {}", productDto.getProductId());

        StoreProduct productToUpdate = findByIdIfExists(productDto.getProductId());
        mapper.updateFromDto(productToUpdate, productDto);

        log.info("Запрос успешно выполнен");
        log.debug("Обновленный товар: {}", productToUpdate.toString());

        return mapper.toProductDto(productToUpdate);
    }

    public Boolean remove(UUID productId) throws ProductNotFoundException {

        log.info("Запрос на удаление товара с id {}", productId.toString());

        StoreProduct storeProduct = findByIdIfExists(productId);
        storeProduct.setProductState(ProductState.DEACTIVATE);

        log.info("Товар с id {} деактивирован", productId);

        return true;
    }

    @Transactional(readOnly = true)
    public ProductDto getById(UUID productId) throws ProductNotFoundException {

        log.info("Запрос товара с id: {}", productId);

        return mapper.toProductDto(findByIdIfExists(productId));
    }

    public Boolean quantityUpdate(SetProductQuantitySetRequest request) throws ProductNotFoundException {

        log.info("Запрос на обновление количества товара с id {}", request.getProductId());

        StoreProduct product = findByIdIfExists(request.getProductId());
        product.setQuantityState(request.getQuantityState());

        log.info("Запрос на обновление количества товара успешно выполнен");
        log.debug("Новое количество товара с id: {} - {}", product.getProductId(), product.getQuantityState());
        return true;
    }

    private StoreProduct findByIdIfExists(UUID productId) throws ProductNotFoundException {
        return repository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Товар с id " + productId + " не найден"));
    }
}
