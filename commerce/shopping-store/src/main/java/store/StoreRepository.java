package store;

import interaction_api.store_feign.model.enums.ProductCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface StoreRepository extends JpaRepository<StoreProduct, UUID> {

    Page<StoreProduct> findAllByProductCategory(ProductCategory category, Pageable pageable);
}
