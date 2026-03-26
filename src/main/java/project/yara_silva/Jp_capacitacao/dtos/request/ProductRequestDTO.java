package project.yara_silva.Jp_capacitacao.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

public record ProductRequestDTO(@NotBlank
                                String nameProduct,

                                @NotBlank
                                String category,

                                @NotBlank
                                String sku,

                                @NotBlank
                                String description,

                                @NotNull
                                BigDecimal price,

                                @NotNull
                                BigDecimal costPrice,

                                @NotNull
                                Integer stockQuantity) {
}
