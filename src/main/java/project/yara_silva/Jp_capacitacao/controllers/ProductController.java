package project.yara_silva.Jp_capacitacao.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.yara_silva.Jp_capacitacao.dtos.request.ProductRequestDTO;
import project.yara_silva.Jp_capacitacao.dtos.request.UpdateProductRequestDTO;
import project.yara_silva.Jp_capacitacao.dtos.response.ProductFullResponseDTO;
import project.yara_silva.Jp_capacitacao.dtos.response.ProductSimpleResponseDTO;
import project.yara_silva.Jp_capacitacao.securityConfig.WebSecurityConfig;
import project.yara_silva.Jp_capacitacao.services.ProductService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/product")
@SecurityRequirement(name = WebSecurityConfig.SECURITY)
public class ProductController {

    @Autowired
    private ProductService productService;

    @Operation(summary = "Cadastra um novo produto")
    @ApiResponse(responseCode = "201",description = "Produto cadastrado com sucesso!")
    @PostMapping("/create")
    public ResponseEntity<ProductFullResponseDTO> createProduct(@RequestBody @Valid ProductRequestDTO body) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.createProduct(body));
    }

    @Operation(summary = "Procurar por todos os produtos")
    @ApiResponse(responseCode = "200",description = "Sucesso")
    @GetMapping("/all")
    public ResponseEntity<List<ProductSimpleResponseDTO>> getAllProduct() {
        return ResponseEntity.status(HttpStatus.OK).body(productService.getAllProducts());
    }

    @Operation(summary = "Procurar por todos os produtos de forma completa")
    @ApiResponse(responseCode = "200",description = "Sucesso")
    @GetMapping("/full{id}")
    public ResponseEntity<ProductFullResponseDTO> getByIdFull(@PathVariable(value = "id")UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.getByIdFull(id));
    }

    @Operation(summary = "Procurar o produto de forma simplificada")
    @ApiResponse(responseCode = "200",description = "Sucesso")
    @GetMapping("/simple{id}")
    public ResponseEntity<ProductSimpleResponseDTO> getByIdSimple(@PathVariable(value = "id")UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.getByIdSimple(id));
    }

    @Operation(summary = "Atualiza produto")
    @ApiResponse(responseCode = "200",description = "Sucesso")
    @PutMapping("/update{id}")
    public ResponseEntity<ProductFullResponseDTO> updateProduct(@PathVariable(value = "id") UUID id, @RequestBody @Valid UpdateProductRequestDTO body) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.updateProduct(id, body));
    }

    @Operation(summary = "Deleta produto")
    @ApiResponse(responseCode = "200",description = "Sucesso")
    @DeleteMapping("/delete{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable UUID id) {
        productService.deleteProduct(id);
        return ResponseEntity.status(HttpStatus.OK).body("Produto deletado com sucesso!");
    }
}
