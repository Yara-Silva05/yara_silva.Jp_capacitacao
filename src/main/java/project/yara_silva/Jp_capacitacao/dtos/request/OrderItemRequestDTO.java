package project.yara_silva.Jp_capacitacao.dtos.request;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record OrderItemRequestDTO(@NotNull
                                  UUID product,

                                  @NotNull
                                  Integer quantity) {
}
