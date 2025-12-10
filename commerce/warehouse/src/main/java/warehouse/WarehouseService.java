package warehouse;

import interaction_api.cart.model.ShoppingCartDto;
import interaction_api.warehouse.model.AddProductToWarehouseRequest;
import interaction_api.warehouse.model.AddressDto;
import interaction_api.warehouse.model.BookedProductsDto;
import interaction_api.warehouse.model.NewProductInWarehouseRequest;
import interaction_api.warehouse.model.exception.NoSpecifiedProductInWarehouseException;
import interaction_api.warehouse.model.exception.SpecifiedProductAlreadyInWarehouseException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class WarehouseService {

    private final WarehouseRepository repository;
    private final WarehouseMapper mapper;

    private static final String[] ADDRESSES =
            new String[]{"ADDRESS_1", "ADDRESS_2"};

    private static final String CURRENT_ADDRESS =
            ADDRESSES[Random.from(new SecureRandom()).nextInt(0, ADDRESSES.length)];

    public void putNewProduct(NewProductInWarehouseRequest newProductInWarehouseRequest)
            throws SpecifiedProductAlreadyInWarehouseException {

        log.info("Добавление нового товара на склад id: {}", newProductInWarehouseRequest.getProductId());
        if (repository.existsById(newProductInWarehouseRequest.getProductId())) {
            log.warn("Товар с id {} уже есть на складе", newProductInWarehouseRequest.getProductId());
            throw new SpecifiedProductAlreadyInWarehouseException("Товар с Id " +
                    newProductInWarehouseRequest.getProductId() + " уже есть на складе");
        } else {
            log.info("Новый товар успешно добавлен id: {}", newProductInWarehouseRequest.getProductId());
            repository.save(mapper.fromDto(newProductInWarehouseRequest));
        }
    }

    public BookedProductsDto check(ShoppingCartDto shoppingCartDto) throws NoSpecifiedProductInWarehouseException {

        log.info("Проверка корзины с id {}", shoppingCartDto.getCartId());
        double deliveryWeight = 0;
        double deliveryVolume = 0;
        boolean isFragile = false;

        Map<UUID, Integer> products = shoppingCartDto.getProducts();
        Set<UUID> productUUIDs = products.keySet();

        List<WarehouseProduct> foundProducts = repository.findAllById(productUUIDs);

        if(foundProducts.size() !=  productUUIDs.size()){
            log.info("Не было найдено {} товаров", productUUIDs.size() - foundProducts.size());
            throw new NoSpecifiedProductInWarehouseException("Некоторые товары не были найдены на складе");
        }

        for(WarehouseProduct product : foundProducts){
            deliveryWeight += product.getWeight();
            deliveryVolume += product.getDepth() + product.getHeight() + product.getWidth();
            isFragile |= product.getFragile();
        }
        log.info("Проверка корзины с id: {} выполнена успешно вес: {}, объем: {}, хрупкость: {}",
                shoppingCartDto.getCartId(), deliveryWeight, deliveryVolume, isFragile);
        return new BookedProductsDto(deliveryWeight, deliveryVolume, isFragile);
    }

    public AddressDto getAddress() {
        log.info("Запрос адреса склада");
        return new AddressDto(CURRENT_ADDRESS,
                CURRENT_ADDRESS,
                CURRENT_ADDRESS,
                CURRENT_ADDRESS,
                CURRENT_ADDRESS);
    }

    public void addProduct(AddProductToWarehouseRequest request) throws NoSpecifiedProductInWarehouseException {

        log.info("Пополнение товара с id: {}", request.getProductId());
        WarehouseProduct product = repository.findById(request.getProductId())
                .orElseThrow(() -> new NoSpecifiedProductInWarehouseException("Товар с id " + request.getProductId()
                        + " на  складе не найден"));

        product.setQuantity(product.getQuantity() + request.getQuantity());
        log.info("Пополнение товара с id {} выполнено успешно, текущее количество: {} ",
                product.getProductId(), product.getQuantity());
    }


}
