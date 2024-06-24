package projeto.services.product_service.domain.product.validations;

import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import projeto.services.product_service.domain.product.ProductRegisterDataDTO;
import projeto.services.product_service.domain.product.ProductRepository;

@Component
@RequiredArgsConstructor
public class NameValidation implements ProductValidation {

    private final ProductRepository repository;

    @Override
    public void valid(ProductRegisterDataDTO data) {
        var optionalProduct = repository.findByName(data.name());

        if(optionalProduct.isPresent()) {
            throw new ValidationException("Procuct with this name already registered");
        }
    }
}
