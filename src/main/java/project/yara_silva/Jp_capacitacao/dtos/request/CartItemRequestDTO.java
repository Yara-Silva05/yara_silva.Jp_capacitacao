package project.yara_silva.Jp_capacitacao.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record CartItemRequestDTO(@NotBlank
                                 @NotNull
                                 UUID idProduct,

                                 @NotNull
                                 Integer quantity) {
}
