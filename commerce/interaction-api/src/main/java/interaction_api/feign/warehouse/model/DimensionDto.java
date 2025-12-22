package interaction_api.feign.warehouse.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Builder
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DimensionDto {

    @NotNull
    @Min(value = 1)
    Double width;

    @NotNull
    @Min(value = 1)
    Double height;

    @NotNull
    @Min(value = 1)
    Double depth;
}
