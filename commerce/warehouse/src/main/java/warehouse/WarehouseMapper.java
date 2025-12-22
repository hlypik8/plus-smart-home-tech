package warehouse;

import interaction_api.feign.warehouse.model.NewProductInWarehouseRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface WarehouseMapper {
    @Mapping(target = "width", source = "productDto.dimensions.width")
    @Mapping(target = "height", source = "productDto.dimensions.height")
    @Mapping(target = "depth", source = "productDto.dimensions.depth")
    @Mapping(target = "quantity", ignore = true)
    WarehouseProduct fromDto(NewProductInWarehouseRequest productDto);
}