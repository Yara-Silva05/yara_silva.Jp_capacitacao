package project.yara_silva.Jp_capacitacao.dtos.response;

import java.math.BigDecimal;
import java.util.UUID;

public record ProductSimpleResponseDTO(UUID id,
                                       String category,
                                       String nameProduct,
                                       String description,
                                       BigDecimal price,
                                       Boolean active) {
}
