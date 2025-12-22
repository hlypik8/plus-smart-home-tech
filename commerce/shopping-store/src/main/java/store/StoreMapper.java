package store;

import interaction_api.feign.store.model.ProductDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;


@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface StoreMapper {

    ProductDto toProductDto(StoreProduct storeProduct);

    StoreProduct toStoreProduct(ProductDto productDto);

    void updateFromDto(@MappingTarget StoreProduct product, ProductDto dto);
}
