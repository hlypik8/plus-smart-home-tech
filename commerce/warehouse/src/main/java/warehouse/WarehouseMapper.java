package warehouse;

import interaction_api.warehouse.model.NewProductInWarehouseRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface WarehouseMapper {

    @Mapping(target = "width", source = "dimensions.width")
    @Mapping(target = "height", source = "dimensions.height")
    @Mapping(target = "depth", source = "dimensions.depth")
    @Mapping(target = "quantity", ignore = true)
    WarehouseProduct toWarehouseProduct(NewProductInWarehouseRequest product);

    @Mapping(target = "width", source = "dimensions.width")
    @Mapping(target = "height", source = "dimensions.height")
    @Mapping(target = "depth", source = "dimensions.depth")
    @Mapping(target = "quantity", ignore = true)
    void updateFromDto(@MappingTarget WarehouseProduct warehouseProduct, NewProductInWarehouseRequest product);
}
