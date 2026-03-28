package project.yara_silva.Jp_capacitacao.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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

    @Operation(summary = "Cria pedido")
    @ApiResponse(responseCode = "201",description = "Pedido criado com sucesso")
    @PostMapping("/create")
    public ResponseEntity<OrderResponseDTO> createOrder(@RequestBody @Valid OrderRequestDTO body) {
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.createOrder(body));
    }

    @Operation(summary = "Busca por ID de pedido")
    @ApiResponse(responseCode = "200",description = "Sucesso")
    @GetMapping("/{id}")
    public ResponseEntity<OrderResponseDTO> getByIdOrder(@PathVariable(value = "id") UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(orderService.getOrder(id));
    }

    @Operation(summary = "Cancela pedido")
    @ApiResponse(responseCode = "200",description = "Sucesso")
    @PostMapping("/cancel{id}")
    public ResponseEntity<String> cancelOrder(@PathVariable UUID id) {
        orderService.cancelOrder(id);
        return ResponseEntity.status(HttpStatus.OK).body("Pedido cancelado com sucesso!");
    }
}

