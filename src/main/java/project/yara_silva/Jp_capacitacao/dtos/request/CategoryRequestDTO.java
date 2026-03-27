package project.yara_silva.Jp_capacitacao.dtos.request;

import jakarta.validation.constraints.NotBlank;

public record CategoryRequestDTO(@NotBlank
                                 String nameCategory,

                                 String categoryParent) {
}
