package projeto.services.orders_service.domain.message;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import projeto.services.orders_service.domain.order.OrderDetailsDTO;
import projeto.services.orders_service.domain.order.OrderService;

@Component
@RequiredArgsConstructor
public class MessageProducer {

    private final AmqpTemplate amqpTemplate;

    private final ObjectMapper mapper;

    @Value("${orders.exchange}")
    private String ordersExchange;

    @Value("${orders.key}")
    private String ordersKey;

    public void sendToOrderQueue(OrderDetailsDTO details) {
        try {
            var json = mapper.writeValueAsString(details);
            amqpTemplate.convertAndSend(ordersExchange, ordersKey, json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
