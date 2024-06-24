package projeto.services.product_service.domain.product;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "product")
@NoArgsConstructor @AllArgsConstructor @Getter @Setter
@EqualsAndHashCode(of = "id")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private BigDecimal price;

    private Integer quantity;

    public Product(ProductRegisterDataDTO data) {
        this.name = data.name();
        this.description = data.description();
        this.price = data.price();
        this.quantity = data.quantity();
    }
}
