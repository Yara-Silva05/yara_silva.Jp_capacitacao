package project.yara_silva.Jp_capacitacao.dtos.response;

import java.math.BigDecimal;

public record ProductSimpleResponseDTO(String category,
                                       String nameProduct,
                                       String description,
                                       BigDecimal price,
                                       Boolean active) {
}
