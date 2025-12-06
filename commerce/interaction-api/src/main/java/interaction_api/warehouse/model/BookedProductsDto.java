package interaction_api.warehouse.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Builder
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookedProductsDto {

    Double deliveryHeight;

    Double deliveryVolume;

    Boolean isFragile;
}
