package project.yara_silva.Jp_capacitacao.dtos.request;

import jakarta.validation.constraints.Email;

public record AuthenticationRequestDTO(String name,
                                       @Email
                                       String email,
                                       String password) {
}
