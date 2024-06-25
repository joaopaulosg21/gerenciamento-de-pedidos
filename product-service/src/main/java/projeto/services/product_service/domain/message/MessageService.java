package projeto.services.product_service.domain.message;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import projeto.services.product_service.domain.order.OrderMessageDTO;
import projeto.services.product_service.domain.product.ProductService;

@Component
@RequiredArgsConstructor
public class MessageService {
    private final ObjectMapper mapper;

    private final ProductService service;

    private final AmqpTemplate amqpTemplate;

    @RabbitListener(queues = {"orders-queue"})
    public void getMessageFromOrderQueue(@Payload Message<?> message) {
        try {
            OrderMessageDTO orderMessage = mapper.readValue(message.getPayload().toString(), OrderMessageDTO.class);

            MessageEventResponse<?> event = service.findAndSumProducts(orderMessage);

            if(event.status().equalsIgnoreCase("sucess")) {
                this.sendMessageToSucessQueue(event);
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendMessageToSucessQueue(MessageEventResponse<?> event) {
        try {
            var json = mapper.writeValueAsString(event);
            amqpTemplate.convertAndSend("order-sucess-queue-exchange",
                    "order-sucess-queue-key", json);
            System.out.println("Mensagem enviada com sucesso para a queue de sucess");
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }
}
