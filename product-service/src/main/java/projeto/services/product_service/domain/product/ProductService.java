package projeto.services.product_service.domain.product;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import projeto.services.product_service.domain.product.validations.ProductValidation;

import java.util.List;

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
}
