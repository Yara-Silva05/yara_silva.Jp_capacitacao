package project.yara_silva.Jp_capacitacao.dtos.response;

import java.math.BigDecimal;

public record ProductFullResponseDTO(String nameProduct,
                                     String category,
                                     String sku,
                                     String description,
                                     BigDecimal price,
                                     BigDecimal costPrice,
                                     Integer stockQuantity) {

}
