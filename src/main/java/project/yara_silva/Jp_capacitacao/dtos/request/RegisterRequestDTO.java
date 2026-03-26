package project.yara_silva.Jp_capacitacao.dtos.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import project.yara_silva.Jp_capacitacao.enums.RoleEnum;

public record RegisterRequestDTO(@NotBlank
                                 String userName,

                                 @NotBlank
                                 @Email
                                 String email,

                                 @NotBlank
                                 String password,

                                 @NotNull
                                 RoleEnum role) {
}
