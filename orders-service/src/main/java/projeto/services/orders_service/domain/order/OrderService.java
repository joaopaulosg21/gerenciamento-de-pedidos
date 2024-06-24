package projeto.services.orders_service.domain.order;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import projeto.services.orders_service.domain.producer.MessageProducer;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository repository;

    private final MessageProducer producer;

    public OrderDetailsDTO create(OrderRegisterDataDTO data) {
        Order order = repository.save(new Order(data));
        OrderDetailsDTO detailsDTO = new OrderDetailsDTO(order);
        producer.sendToOrderQueue(detailsDTO);

        return detailsDTO;
    }
}
