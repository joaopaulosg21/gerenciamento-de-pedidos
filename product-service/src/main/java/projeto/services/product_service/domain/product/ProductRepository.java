package projeto.services.product_service.domain.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product,Long> {

    Optional<Product> findByName(String name);

    @Query("SELECT p FROM Product as p WHERE p.id=:id AND p.quantity>=:quantity")
    Product findByIdAndQuantity(Long id, Integer quantity);
}
