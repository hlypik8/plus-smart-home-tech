package interaction_api.warehouse.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Builder
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DimensionDto {

    Double width;

    Double height;

    Double depth;

    Long quantity = 0L;
}
