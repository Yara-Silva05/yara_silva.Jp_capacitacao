package project.yara_silva.Jp_capacitacao.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import project.yara_silva.Jp_capacitacao.enums.OrderStatusEnum;

import java.math.BigDecimal;
import java.util.UUID;

public record OrderRequestDTO(@NotNull
                              UUID user,

                              @NotBlank
                              String address,

                              @NotNull
                              BigDecimal freight,

                              @NotNull
                              BigDecimal total,

                              @NotNull
                              OrderStatusEnum status) {
}
