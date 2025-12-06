package warehouse;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "warehouse_products")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WarehouseProduct {

    UUID productId;

    boolean isFragile;

    double width;

    double height;

    double depth;

    double weight;

    Long quantity;
}
