package projeto.services.product_service.domain.order;

import com.fasterxml.jackson.annotation.JsonAlias;

import java.util.List;

public record OrderMessageDTO(List<OrderItemDTO> items, @JsonAlias("id") String orderId) {
}
