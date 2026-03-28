package project.yara_silva.Jp_capacitacao.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.yara_silva.Jp_capacitacao.dtos.response.InventoryTransactionResponseDTO;
import project.yara_silva.Jp_capacitacao.securityConfig.WebSecurityConfig;
import project.yara_silva.Jp_capacitacao.services.InventoryTransactionService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/inventory")
@SecurityRequirement(name = WebSecurityConfig.SECURITY)
public class InventoryTransactionController {

    @Autowired
    InventoryTransactionService inventoryTransactionService;

    @Operation(summary = "Busca todas as transações de inventário")
    @ApiResponse(responseCode = "200", description = "Sucesso")
    @PreAuthorize("hasAnyRole('SELLER', 'ADMIN')")
    @GetMapping("/all")
    public ResponseEntity<List<InventoryTransactionResponseDTO>> getAllInventoryTransactions() {
        return ResponseEntity.status(HttpStatus.OK).body(inventoryTransactionService.getAllinventoryTransactions());
    }

    @Operation(summary = "Busca transações de inventário pelo ID do produto")
    @ApiResponse(responseCode = "200", description = "Sucesso")
    @PreAuthorize("hasAnyRole('SELLER', 'ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<List<InventoryTransactionResponseDTO>> getByProductId(@PathVariable(value = "id") UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(inventoryTransactionService.getByProductId(id));
    }
}