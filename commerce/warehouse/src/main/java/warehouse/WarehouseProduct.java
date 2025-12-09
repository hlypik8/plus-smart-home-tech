package warehouse;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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

    @Id
    @Column(name = "product_id")
    UUID productId;

    @Column(name = "fragile")
    Boolean isFragile;

    @Column(name = "width")
    Double width;

    @Column(name = "height")
    Double height;

    @Column(name = "depth")
    Double depth;

    @Column(name = "weight")
    Double weight;

    @Column(name = "quantity")
    Long quantity = 0L;
}
