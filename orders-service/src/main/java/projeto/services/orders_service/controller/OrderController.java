package projeto.services.orders_service.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import projeto.services.orders_service.domain.order.OrderDetailsDTO;
import projeto.services.orders_service.domain.order.OrderRegisterDataDTO;
import projeto.services.orders_service.domain.order.OrderService;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService service;

    @PostMapping
    public ResponseEntity<OrderDetailsDTO> create(@RequestBody @Valid OrderRegisterDataDTO data) {

        return ResponseEntity.ok(service.create(data));
    }
}
