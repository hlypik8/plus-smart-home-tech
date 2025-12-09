package interaction_api.store.model;

import interaction_api.store.model.enums.QuantityState;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class SetProductQuantitySetRequest {

    UUID productId;

    QuantityState quantityState;
}
