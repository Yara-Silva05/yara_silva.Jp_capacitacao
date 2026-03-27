package project.yara_silva.Jp_capacitacao.dtos.response;

import project.yara_silva.Jp_capacitacao.enums.OrderStatusEnum;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public record OrderResponseDTO(UUID user,

                               String address,

                               BigDecimal freight,

                               BigDecimal total,

                               OrderStatusEnum status,

                               List<OrderItemResponseDTO> items) {
}
