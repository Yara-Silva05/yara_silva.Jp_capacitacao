package project.yara_silva.Jp_capacitacao.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.yara_silva.Jp_capacitacao.dtos.request.AuthenticationRequestDTO;
import project.yara_silva.Jp_capacitacao.dtos.response.LoginResponseDTO;
import project.yara_silva.Jp_capacitacao.dtos.request.RegisterRequestDTO;
import project.yara_silva.Jp_capacitacao.services.AuthenticationService;

@RestController
@RequestMapping("auth")
public class AuthenticationController {

    @Autowired
    AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid AuthenticationRequestDTO body){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body((authenticationService.login(body)));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterRequestDTO body){
        authenticationService.register(body);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}

