package project.yara_silva.Jp_capacitacao.dtos.request;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record CartItemRequestDTO(@NotNull
                                 UUID idProduct,

                                 @NotNull
                                 Integer quantity) {
}
