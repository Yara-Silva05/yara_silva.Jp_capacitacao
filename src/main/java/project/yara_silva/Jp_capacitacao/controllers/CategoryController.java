package project.yara_silva.Jp_capacitacao.controllers;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @PostMapping("/create")
    public ResponseEntity<CategoryResponseDTO> createCategory(@RequestBody @Valid CategoryRequestDTO body) {
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.createCategory(body));
    }

    @GetMapping("/all")
    public ResponseEntity<List<CategoryResponseDTO>> getAllProduct() {
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.getAllCategories());
    }

    @PutMapping("/update{id}")
    public ResponseEntity<CategoryResponseDTO> updateCategory(@PathVariable(value = "id") UUID id, @RequestBody @Valid UpdateCategoryRequestDTO body) {
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.updateCategory(id, body));
    }

    @DeleteMapping("/delete{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable UUID id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.status(HttpStatus.OK).body("Categoria deletada com sucesso!");
    }
}
