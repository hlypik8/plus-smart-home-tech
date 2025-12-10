package cart;

import interaction_api.cart.model.ShoppingCartDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface CartMapper {

    @Mapping(source = "cartId", target = "shoppingCartId")
    @Mapping(source = "products", target = "products")
    ShoppingCartDto toDto(Cart cart);

    @Mapping(source = "shoppingCartId", target = "cartId")
    @Mapping(source = "products", target = "products")
    Cart toEntity(ShoppingCartDto shoppingCartDto);

    @Mapping(source = "shoppingCartId", target = "cartId")
    @Mapping(source = "products", target = "products")
    void updateFromDto(@MappingTarget Cart cart, ShoppingCartDto shoppingCartDto);

    default Map<UUID, Integer> mapToDto(Map<UUID, Long> source) {
        if (source == null) return null;
        return source.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().intValue()));
    }

    default Map<UUID, Long> mapToEntity(Map<UUID, Integer> source) {
        if (source == null) return null;
        return source.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().longValue()));
    }
}
