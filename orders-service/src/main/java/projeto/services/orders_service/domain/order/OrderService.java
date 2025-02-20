package projeto.services.orders_service.domain.order;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import projeto.services.orders_service.domain.message.MessageProducer;
import projeto.services.orders_service.domain.message.MessageEventResponse;
import projeto.services.orders_service.domain.message.QueueResponse;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository repository;

    private final MessageProducer messageProducer;

    public OrderDetailsDTO create(OrderRegisterDataDTO data) {
        Order order = repository.save(new Order(data));
        OrderDetailsDTO detailsDTO = new OrderDetailsDTO(order);
        messageProducer.sendToOrderQueue(detailsDTO);

        return detailsDTO;
    }

    public void updateSucessOrder(MessageEventResponse<QueueResponse> eventResponse) {
        var id = UUID.fromString(eventResponse.response().orderId());
        Optional<Order> optionalOrder = repository.findById(id);

        if(optionalOrder.isPresent()){
            Order order = optionalOrder.get();
            order.setAmount(new BigDecimal(eventResponse.response().message()));
            order.setStatus(Status.DELIVERED);
            repository.save(order);
        }
    }

    public void updateErrorOrder(MessageEventResponse<QueueResponse> eventResponse) {
        var id = UUID.fromString(eventResponse.response().orderId());
        Optional<Order> optionalOrder = repository.findById(id);

        if(optionalOrder.isPresent()){
            Order order = optionalOrder.get();
            order.setStatus(Status.CANCELED);
            repository.save(order);
        }
    }

    public OrderDetailsDTO findById(UUID id) {
        Order order = repository.findById(id).orElseThrow(RuntimeException::new);
        return new OrderDetailsDTO(order);
    }
}
