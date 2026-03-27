package project.yara_silva.Jp_capacitacao.dtos.response;

import java.math.BigDecimal;
import java.util.UUID;

public record OrderItemResponseDTO(UUID order,
                                   ProductSimpleResponseDTO product,
                                   Integer quantity,
                                   BigDecimal priceSnapshot) {
}
