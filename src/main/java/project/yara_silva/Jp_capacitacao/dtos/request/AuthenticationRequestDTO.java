package project.yara_silva.Jp_capacitacao.dtos.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record AuthenticationRequestDTO(@NotBlank
                                       String userName,

                                       @NotBlank
                                       @Email
                                       String email,

                                       @NotBlank
                                       String password) {
}
