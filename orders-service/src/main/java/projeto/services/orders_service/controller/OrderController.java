package projeto.services.orders_service.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projeto.services.orders_service.domain.order.OrderDetailsDTO;
import projeto.services.orders_service.domain.order.OrderRegisterDataDTO;
import projeto.services.orders_service.domain.order.OrderService;

import java.util.UUID;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService service;

    @PostMapping
    public ResponseEntity<OrderDetailsDTO> create(@RequestBody @Valid OrderRegisterDataDTO data) {

        return ResponseEntity.ok(service.create(data));
    }

    @GetMapping("/status/{id}")
    public ResponseEntity<OrderDetailsDTO> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(service.findById(id));
    }
}
