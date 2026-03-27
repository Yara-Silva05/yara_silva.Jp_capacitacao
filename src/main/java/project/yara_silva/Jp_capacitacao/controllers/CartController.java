package project.yara_silva.Jp_capacitacao.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.yara_silva.Jp_capacitacao.dtos.request.CartItemRequestDTO;
import project.yara_silva.Jp_capacitacao.dtos.request.UpdateCartItemRequestDTO;
import project.yara_silva.Jp_capacitacao.dtos.response.CartItemResponseDTO;
import project.yara_silva.Jp_capacitacao.services.CartService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    CartService cartService;

    @PostMapping("/create")
    public ResponseEntity<CartItemResponseDTO> createCartItem(@RequestBody @Valid CartItemRequestDTO body) {
        return ResponseEntity.status(HttpStatus.CREATED).body(cartService.createCartItem(body));
    }

    @GetMapping("/all")
    public ResponseEntity<List<CartItemResponseDTO>> getAllCartItems() {
        return ResponseEntity.status(HttpStatus.OK).body(cartService.getAllCartItem());
    }

    @PutMapping("/update{id}")
    public ResponseEntity<CartItemResponseDTO> updateCartItem(@PathVariable(value = "id") UUID id, @RequestBody @Valid UpdateCartItemRequestDTO body) {
        return ResponseEntity.status(HttpStatus.OK).body(cartService.updateCartItem(id, body));
    }

    @DeleteMapping("/delete{id}")
    public ResponseEntity<String> deleteCartItem(@PathVariable UUID id) {
        cartService.deleteCartItem(id);
        return ResponseEntity.status(HttpStatus.OK).body("Carrinho deletado com sucesso!");
    }
}
