package project.yara_silva.Jp_capacitacao.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.yara_silva.Jp_capacitacao.dtos.request.CartItemRequestDTO;
import project.yara_silva.Jp_capacitacao.dtos.request.UpdateCartItemRequestDTO;
import project.yara_silva.Jp_capacitacao.dtos.response.CartItemResponseDTO;
import project.yara_silva.Jp_capacitacao.securityConfig.WebSecurityConfig;
import project.yara_silva.Jp_capacitacao.services.CartService;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/cart")
@SecurityRequirement(name = WebSecurityConfig.SECURITY)
public class CartController {

    @Autowired
    CartService cartService;

    @Operation(summary = "Cria item no carrinho")
    @ApiResponse(responseCode = "201", description = "Item criado com sucesso")
    @PostMapping("/create")
    public ResponseEntity<CartItemResponseDTO> createCartItem(@RequestBody @Valid CartItemRequestDTO body) {
        return ResponseEntity.status(HttpStatus.CREATED).body(cartService.createCartItem(body));
    }

    @Operation(summary = "Busca todos os itens do carrinho")
    @ApiResponse(responseCode = "200", description = "Sucesso")
    @GetMapping("/all")
    public ResponseEntity<List<CartItemResponseDTO>> getAllCartItems() {
        return ResponseEntity.status(HttpStatus.OK).body(cartService.getAllCartItem());
    }

    @Operation(summary = "Atualiza item do carrinho")
    @ApiResponse(responseCode = "200", description = "Sucesso")
    @PutMapping("/update/{id}")
    public ResponseEntity<CartItemResponseDTO> updateCartItem(@PathVariable(value = "id") UUID id, @RequestBody @Valid UpdateCartItemRequestDTO body) {
        return ResponseEntity.status(HttpStatus.OK).body(cartService.updateCartItem(id, body));
    }

    @Operation(summary = "Deleta item do carrinho")
    @ApiResponse(responseCode = "200", description = "Sucesso")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCartItem(@PathVariable UUID id) {
        cartService.deleteCartItem(id);
        return ResponseEntity.status(HttpStatus.OK).body("Carrinho deletado com sucesso!");
    }
}
