package project.yara_silva.Jp_capacitacao.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import project.yara_silva.Jp_capacitacao.dtos.request.CategoryRequestDTO;
import project.yara_silva.Jp_capacitacao.dtos.request.UpdateCategoryRequestDTO;
import project.yara_silva.Jp_capacitacao.dtos.response.CategoryResponseDTO;
import project.yara_silva.Jp_capacitacao.securityConfig.WebSecurityConfig;
import project.yara_silva.Jp_capacitacao.services.CategoryService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/category")
@SecurityRequirement(name = WebSecurityConfig.SECURITY)
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @Operation(summary = "Cria categoria")
    @ApiResponse(responseCode = "201", description = "Categoria criada com sucesso")
    @PreAuthorize("hasAnyRole('SELLER', 'ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<CategoryResponseDTO> createCategory(@RequestBody @Valid CategoryRequestDTO body) {
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.createCategory(body));
    }

    @Operation(summary = "Busca todas as categorias")
    @ApiResponse(responseCode = "200", description = "Sucesso")
    @GetMapping("/all")
    public ResponseEntity<List<CategoryResponseDTO>> getAllCategories() {
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.getAllCategories());
    }

    @Operation(summary = "Atualiza categoria")
    @ApiResponse(responseCode = "200", description = "Sucesso")
    @PreAuthorize("hasAnyRole('SELLER', 'ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<CategoryResponseDTO> updateCategory(@PathVariable(value = "id") UUID id, @RequestBody @Valid UpdateCategoryRequestDTO body) {
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.updateCategory(id, body));
    }

    @Operation(summary = "Deleta categoria")
    @ApiResponse(responseCode = "200", description = "Sucesso")
    @PreAuthorize("hasAnyRole('SELLER', 'ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable UUID id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.status(HttpStatus.OK).body("Categoria deletada com sucesso!");
    }
}