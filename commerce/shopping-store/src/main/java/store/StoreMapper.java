package store;

import interaction_api.store.model.ProductDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface StoreMapper {

    @Mapping(source = "name", target = "productName")
    ProductDto toProductDto(StoreProduct storeProduct);

    @Mapping(source = "productName", target = "name")
    StoreProduct toStoreProduct(ProductDto productDto);

    @Mapping(source = "productName", target = "name")
    void updateFromDto(@MappingTarget StoreProduct storeProduct, ProductDto productDto);
}
