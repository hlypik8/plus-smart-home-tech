package interaction_api.warehouse.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@Builder
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class NewProductInWarehouseRequest {

    UUID productId;

    Boolean isFragile;

    DimensionDto dimensions;

    Double weight;
}
