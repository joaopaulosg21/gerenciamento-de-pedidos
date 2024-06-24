package projeto.services.orders_service.domain.order;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@NoArgsConstructor @AllArgsConstructor @Getter @Setter
public class OrderItem {

    private Long id;

    private Integer quantity;

    public OrderItem(OrderProductDTO dto) {
        this.id = dto.id();
        this.quantity = dto.quantity();
    }
}
