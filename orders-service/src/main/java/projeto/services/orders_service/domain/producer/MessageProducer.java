package projeto.services.orders_service.domain.producer;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Component;
import projeto.services.orders_service.domain.order.OrderDetailsDTO;

@Component
@RequiredArgsConstructor
public class MessageProducer {

    private final AmqpTemplate amqpTemplate;

    private final ObjectMapper mapper;

    public void sendToOrderQueue(OrderDetailsDTO details) {
        System.out.println(details + " Enviado para a fila");
    }
}
