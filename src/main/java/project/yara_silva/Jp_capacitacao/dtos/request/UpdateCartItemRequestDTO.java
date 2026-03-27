package project.yara_silva.Jp_capacitacao.dtos.request;

import jakarta.validation.constraints.NotNull;

public record UpdateCartItemRequestDTO(@NotNull
                                       Integer quantity) {
}
