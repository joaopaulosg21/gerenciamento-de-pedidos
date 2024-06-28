package projeto.services.orders_service.domain.order;

import java.util.List;
import java.util.UUID;

public record OrderDetailsDTO(List<OrderProductDTO> items, UUID id, Status status) {
    public OrderDetailsDTO(Order order) {
        this(order.items.stream().map(OrderProductDTO::new).toList(),
                order.getId(),order.getStatus());
    }
}
