package projeto.services.orders_service.domain.order;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public record OrderRegisterDataDTO(@NotNull List<OrderProductDTO> items) {
}
