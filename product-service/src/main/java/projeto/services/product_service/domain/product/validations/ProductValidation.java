package projeto.services.product_service.domain.product.validations;

import projeto.services.product_service.domain.product.ProductRegisterDataDTO;

public interface ProductValidation {

    void valid(ProductRegisterDataDTO data);
}
