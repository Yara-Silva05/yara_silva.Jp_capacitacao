package project.yara_silva.Jp_capacitacao.dtos.response;

import project.yara_silva.Jp_capacitacao.enums.InventoryReasonEnum;

import java.time.LocalDateTime;
import java.util.UUID;

public record InventoryTransactionResponseDTO(UUID id,
                                              ProductSimpleResponseDTO product,
                                              Integer delta,
                                              InventoryReasonEnum reason,
                                              UUID orderId,
                                              UUID userId,
                                              LocalDateTime createdAt) {
}
