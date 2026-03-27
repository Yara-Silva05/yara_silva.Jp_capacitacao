package project.yara_silva.Jp_capacitacao.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.yara_silva.Jp_capacitacao.dtos.request.ProductRequestDTO;
import project.yara_silva.Jp_capacitacao.dtos.request.UpdateProductRequestDTO;
import project.yara_silva.Jp_capacitacao.dtos.response.ProductFullResponseDTO;
import project.yara_silva.Jp_capacitacao.dtos.response.ProductSimpleResponseDTO;
import project.yara_silva.Jp_capacitacao.services.ProductService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/create")
    public ResponseEntity<ProductFullResponseDTO> createProduct(@RequestBody @Valid ProductRequestDTO body) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.createProduct(body));
    }

    @GetMapping("/all")
    public ResponseEntity<List<ProductSimpleResponseDTO>> getAllProduct() {
        return ResponseEntity.status(HttpStatus.OK).body(productService.getAllProducts());
    }

    @GetMapping("/full{id}")
    public ResponseEntity<ProductFullResponseDTO> getByIdFull(@PathVariable(value = "id")UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.getByIdFull(id));
    }

    @GetMapping("/simple{id}")
    public ResponseEntity<ProductSimpleResponseDTO> getByIdSimple(@PathVariable(value = "id")UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.getByIdSimple(id));
    }

    @PutMapping("/update{id}")
    public ResponseEntity<ProductFullResponseDTO> updateProduct(@PathVariable(value = "id") UUID id, @RequestBody @Valid UpdateProductRequestDTO body) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.updateProduct(id, body));
    }

    @DeleteMapping("/delete{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable UUID id) {
        productService.deleteProduct(id);
        return ResponseEntity.status(HttpStatus.OK).body("Produto deletado com sucesso!");
    }
}
