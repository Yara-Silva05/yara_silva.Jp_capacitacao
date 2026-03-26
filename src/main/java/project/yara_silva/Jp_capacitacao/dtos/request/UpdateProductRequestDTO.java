package project.yara_silva.Jp_capacitacao.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record UpdateProductRequestDTO(@NotBlank
                                      String category,

                                      @NotBlank
                                      String nameProduct,

                                      @NotBlank
                                      String description,

                                      @NotNull
                                      BigDecimal price,

                                      @NotNull
                                      BigDecimal costPrice,

                                      @NotNull
                                      Boolean active) {
}
