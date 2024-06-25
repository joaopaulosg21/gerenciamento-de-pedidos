package projeto.services.product_service.domain.product;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import projeto.services.product_service.domain.message.MessageEventResponse;
import projeto.services.product_service.domain.message.QueueResponse;
import projeto.services.product_service.domain.order.OrderItemDTO;
import projeto.services.product_service.domain.order.OrderMessageDTO;
import projeto.services.product_service.domain.product.validations.ProductValidation;
import projeto.services.product_service.infra.exceptions.ProductNotFoundException;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repository;

    private final List<ProductValidation> validations;

    public ProductDetailsDTO register(ProductRegisterDataDTO data) {
        validations.forEach(v -> v.valid(data));

        Product product = repository.save(new Product(data));

        return new ProductDetailsDTO(product);
    }

    public ProductDetailsDTO findById(Long id) {
        var optionalProduct = repository.findById(id);

        if(optionalProduct.isEmpty()) {
            throw new ProductNotFoundException("Product with this id not found");
        }

        return new ProductDetailsDTO(optionalProduct.get());
    }

    public MessageEventResponse<?> findAndSumProducts(OrderMessageDTO orderMessageDTO) {
        var response = orderMessageDTO.items().stream().map(o -> repository.findByIdAndQuantity(o.id(),o.quantity()))
                .filter(Objects::nonNull)
                .toList();
        if(response.size() != orderMessageDTO.items().size()) {
            return new MessageEventResponse<>("error",
                    new QueueResponse("Item id or quantity invalid",orderMessageDTO.orderId()));
        }

        var totalAmount = response.stream().map(p -> {
            int quantity = orderMessageDTO.items().stream()
                    .filter(i -> i.id() == p.getId()).map(OrderItemDTO::quantity)
                    .mapToInt(Integer::intValue).sum();
            return p.getPrice().multiply(new BigDecimal(quantity));
        }).reduce(BigDecimal::add).get();

        return new MessageEventResponse<>("sucess",totalAmount);
    }
}
