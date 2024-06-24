package projeto.services.product_service.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import projeto.services.product_service.domain.product.ProductDetailsDTO;
import projeto.services.product_service.domain.product.ProductRegisterDataDTO;
import projeto.services.product_service.domain.product.ProductService;

import java.net.URI;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService service;

    @PostMapping
    public ResponseEntity<ProductDetailsDTO> register(@RequestBody @Valid ProductRegisterDataDTO data,
                                                      UriComponentsBuilder builder) {
        var productDetails = service.register(data);
        URI uri = builder.path("/products/{id}").buildAndExpand(productDetails.id()).toUri();
        return ResponseEntity.created(uri).body(productDetails);
    }
}