package store;

import interaction_api.store.model.ProductDto;
import interaction_api.store.model.enums.ProductCategory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class StoreService {

    private final StoreRepository repository;
    private final StoreMapper mapper;

    public Page<ProductDto> getByCategory(ProductCategory category, Pageable pageable){

        log.info("Запрос товаров в категории {} с параметрами страницы {}", category, pageable.toString());

        Page<StoreProduct> product = repository.findAllByProductCategory(category, pageable);

        log.info("Запрос выполнен успешно");
        log.debug("Получено товаров {}", product.getTotalElements());

        return product.map((mapper::toProductDto));
    }

    public ProductDto add(ProductDto productDto){

        log.info("Запрос на добавление нового товара {}", productDto.getProductName());
        StoreProduct product = mapper.toStoreProduct(productDto);
        StoreProduct savedProduct = repository.save(product);
        log.info("Запрос выполнен успешно");
        log.debug("Добавлен новый продукт id {}", savedProduct.getProductId());

        return mapper.toProductDto(savedProduct);

    }
}
