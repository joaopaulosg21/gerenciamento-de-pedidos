package projeto.services.orders_service.domain.message;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import projeto.services.orders_service.domain.order.OrderService;

@Component
@RequiredArgsConstructor
public class MessageConsumer {

    private final ObjectMapper mapper;

    private final OrderService service;

    @RabbitListener(queues = "order-sucess-queue")
    public void getMessageFromSucessQueue(@Payload Message message) {
        try {
            MessageEventResponse<QueueResponse> messageResponse = mapper.readValue(message.getPayload().toString(),
                    new TypeReference<>() {});
            System.out.println(messageResponse);
            service.updateSucessOrder(messageResponse);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
