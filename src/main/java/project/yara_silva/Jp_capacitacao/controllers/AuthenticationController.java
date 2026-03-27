package project.yara_silva.Jp_capacitacao.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.yara_silva.Jp_capacitacao.dtos.request.AuthenticationRequestDTO;
import project.yara_silva.Jp_capacitacao.dtos.response.LoginResponseDTO;
import project.yara_silva.Jp_capacitacao.dtos.request.RegisterRequestDTO;
import project.yara_silva.Jp_capacitacao.securityConfig.WebSecurityConfig;
import project.yara_silva.Jp_capacitacao.services.AuthenticationService;

@RestController
@RequestMapping("/auth")
@SecurityRequirement(name = WebSecurityConfig.SECURITY)
@Tag(name = "yara_silva.jp_capacitação", description = "Autentificação do usuário.")
public class AuthenticationController {

    @Autowired
    AuthenticationService authenticationService;

    @PostMapping("/register")
    @Operation(summary = "Cadastra um novo usuário")
    @ApiResponse(responseCode = "201",description = "Usuário cadastrado com sucesso!")
    public ResponseEntity register(@RequestBody @Valid RegisterRequestDTO body){
        authenticationService.register(body);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/login")
    @Operation(summary = "Conecta usuário já cadastrado")
    @ApiResponse(responseCode = "202", description = "Usuário conectado com sucesso!")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid AuthenticationRequestDTO body){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body((authenticationService.login(body)));
    }
}

