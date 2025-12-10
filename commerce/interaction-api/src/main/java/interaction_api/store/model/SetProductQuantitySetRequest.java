package interaction_api.store.model;

import interaction_api.store.model.enums.QuantityState;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SetProductQuantitySetRequest {

    UUID productId;

    QuantityState quantityState;
}
