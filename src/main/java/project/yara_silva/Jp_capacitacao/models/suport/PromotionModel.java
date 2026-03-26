package project.yara_silva.Jp_capacitacao.models.suport;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import project.yara_silva.Jp_capacitacao.enums.PromotionTypeEnum;
import project.yara_silva.Jp_capacitacao.exceptions.PromotionInvalidException;
import project.yara_silva.Jp_capacitacao.models.main.CategoryModel;
import project.yara_silva.Jp_capacitacao.models.main.OrderItemModel;
import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
@Table(name = "tb_promotion")
public class PromotionModel implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(unique = true, nullable = false)
    private String code;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PromotionTypeEnum type;

    @Column(nullable = false)
    private BigDecimal value;

    @Column(nullable = false)
    private LocalDateTime validFrom;

    @Column(nullable = false)
    private LocalDateTime validTo;

    @Column(nullable = false)
    private Integer usageLimit;

    @Column(nullable = false)
    private Integer usedCount;

    @Column(nullable = false)
    private Set<CategoryModel> applicableTo = new HashSet<>();

    @Column(nullable = false)
    private BigDecimal minimumValue;

    private Boolean promotionValidation(OrderItemModel item) {
        usedCount += item.getQuantity();
        if (usedCount <= usageLimit) {
            if (item.getProduct().getPrice().compareTo(minimumValue) >= 0) {
                if (applicableTo.contains(item.getProduct().getCategory()) || applicableTo.isEmpty()) {
                    return true;
                }else {
                    throw new PromotionInvalidException("O produto não atingiu o valor mínimo.");
                }
            } else {
                throw new PromotionInvalidException("O cupom não aceita essa categoria.");
            }
        }else {
            throw new PromotionInvalidException("O cupom passou do limite de uso.");
        }
    }
}
