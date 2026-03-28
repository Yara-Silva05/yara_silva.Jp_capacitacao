package project.yara_silva.Jp_capacitacao.controllers;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.yara_silva.Jp_capacitacao.dtos.request.OrderRequestDTO;
import project.yara_silva.Jp_capacitacao.dtos.response.OrderResponseDTO;
import project.yara_silva.Jp_capacitacao.securityConfig.WebSecurityConfig;
import project.yara_silva.Jp_capacitacao.services.OrderService;

import java.util.UUID;

@RestController
@RequestMapping("/order")
@SecurityRequirement(name = WebSecurityConfig.SECURITY)
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping("/create")
    public ResponseEntity<OrderResponseDTO> createOrder(@RequestBody @Valid OrderRequestDTO body) {
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.createOrder(body));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponseDTO> getByIdOrder(@PathVariable(value = "id") UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(orderService.getOrder(id));
    }

    @PostMapping("/cancel{id}")
    public ResponseEntity<String> cancelOrder(@PathVariable UUID id) {
        orderService.cancelOrder(id);
        return ResponseEntity.status(HttpStatus.OK).body("Pedido cancelado com sucesso!");
    }
}

