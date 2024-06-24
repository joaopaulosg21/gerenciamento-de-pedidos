package projeto.services.orders_service.domain.order;

public record OrderProductDTO(Long id, Integer quantity) {

    public OrderProductDTO(OrderItem orderItem) {
        this(orderItem.getId(), orderItem.getQuantity());
    }
}
