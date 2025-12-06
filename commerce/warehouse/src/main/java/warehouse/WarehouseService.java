package warehouse;

import interaction_api.warehouse.model.AddProductToWarehouseRequest;
import interaction_api.warehouse.model.NewProductInWarehouseRequest;
import interaction_api.warehouse.model.exception.NoSpecifiedProductInWarehouseException;
import interaction_api.warehouse.model.exception.SpecifiedProductAlreadyInWarehouseException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.util.Random;

@Service
@RequiredArgsConstructor
@Transactional
public class WarehouseService {

    private final WarehouseRepository repository;

    private static final String[] ADDRESSES =
            new String[] {"ADDRESS_1", "ADDRESS_2"};

    private static final String CURRENT_ADDRESS =
            ADDRESSES[Random.from(new SecureRandom()).nextInt(0, ADDRESSES.length)];


    public void add(AddProductToWarehouseRequest request) throws NoSpecifiedProductInWarehouseException {

        WarehouseProduct product = repository.findById(request.getProductId())
                .orElseThrow(() -> new NoSpecifiedProductInWarehouseException("Товар с id " + request.getProductId()
                        + " на  складе не найден"));

        product.setQuantity(product.getQuantity() + request.getQuantity());
    }

    public void put(NewProductInWarehouseRequest newProductInWarehouseRequest)
            throws SpecifiedProductAlreadyInWarehouseException {

        if(repository.existsById(newProductInWarehouseRequest.getProductId())){
            throw new SpecifiedProductAlreadyInWarehouseException("Товар с Id " +
                    newProductInWarehouseRequest.getProductId() + " уже есть на складе");
        }else {
            //Добавить маппер
            repository.save(newProductInWarehouseRequest)
        }
    }

}
