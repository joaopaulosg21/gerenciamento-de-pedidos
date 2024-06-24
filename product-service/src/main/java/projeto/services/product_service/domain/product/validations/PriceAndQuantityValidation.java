package projeto.services.product_service.domain.product.validations;

import jakarta.validation.ValidationException;
import org.springframework.stereotype.Component;
import projeto.services.product_service.domain.product.ProductRegisterDataDTO;

@Component
public class PriceAndQuantityValidation implements ProductValidation{

    @Override
    public void valid(ProductRegisterDataDTO data) {
        if(data.price().doubleValue() <= 0) {
            throw new ValidationException("Price cannot be less than or equal to 0");
        }

        if(data.quantity() <= 0) {
            throw new ValidationException("Quantity cannot be less than or equal to 0");
        }
    }
}
