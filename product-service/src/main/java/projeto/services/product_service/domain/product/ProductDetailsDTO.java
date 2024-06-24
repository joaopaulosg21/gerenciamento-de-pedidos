package projeto.services.product_service.domain.product;

import java.math.BigDecimal;

public record ProductDetailsDTO(Long id, String name, String description, BigDecimal price, Integer quantity) {

    public ProductDetailsDTO(Product product) {
        this(product.getId(), product.getName(), product.getDescription(), product.getPrice(), product.getQuantity());
    }
}
