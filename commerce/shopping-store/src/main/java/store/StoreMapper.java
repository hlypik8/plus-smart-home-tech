package store;

import interaction_api.store.model.ProductDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface StoreMapper {

    @Mapping(source = "name", target = "productName")
    @Mapping(source = "productId", target = "productId")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "imageSrc", target = "imageSrc")
    @Mapping(source = "quantityState", target = "quantityState")
    @Mapping(source = "productState", target = "productState")
    @Mapping(source = "productCategory", target = "productCategory")
    @Mapping(source = "price", target = "price")
    ProductDto toProductDto(StoreProduct storeProduct);

    @Mapping(source = "productName", target = "name")
    @Mapping(source = "productId", target = "productId")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "imageSrc", target = "imageSrc")
    @Mapping(source = "quantityState", target = "quantityState")
    @Mapping(source = "productState", target = "productState")
    @Mapping(source = "productCategory", target = "productCategory")
    @Mapping(source = "price", target = "price")
    StoreProduct toStoreProduct(ProductDto productDto);

    @Mapping(source = "productName", target = "name")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "imageSrc", target = "imageSrc")
    @Mapping(source = "quantityState", target = "quantityState")
    @Mapping(source = "productState", target = "productState")
    @Mapping(source = "productCategory", target = "productCategory")
    @Mapping(source = "price", target = "price")
    void updateFromDto(@MappingTarget StoreProduct storeProduct, ProductDto productDto);
}
