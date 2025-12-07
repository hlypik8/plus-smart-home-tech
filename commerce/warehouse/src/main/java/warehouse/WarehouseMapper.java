package warehouse;

import interaction_api.warehouse.model.NewProductInWarehouseRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface WarehouseMapper {

    @Mapping(target = "width", source = "product.dimensions.width")
    @Mapping(target = "height", source = "product.dimensions.height")
    @Mapping(target = "depth", source = "product.dimensions.depth")
    @Mapping(target = "quantity", ignore = true)
    WarehouseProduct toWarehouseProduct(NewProductInWarehouseRequest product);
}
