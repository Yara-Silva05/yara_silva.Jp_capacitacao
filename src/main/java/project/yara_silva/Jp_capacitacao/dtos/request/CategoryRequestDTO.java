package project.yara_silva.Jp_capacitacao.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CategoryRequestDTO(@NotBlank
                                 @NotNull
                                 String nameCategory,

                                 String categoryParent) {
}
