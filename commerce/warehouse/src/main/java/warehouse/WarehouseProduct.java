package warehouse;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.UuidGenerator;

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
    Boolean fragile;

    @Column(name = "width")
    Double width;

    @Column(name = "height")
    Double height;

    @Column(name = "depth")
    Double depth;

    @Column(name = "weight")
    Double weight;

    @Column(name = "quantity")
    long quantity = 0L;
}
