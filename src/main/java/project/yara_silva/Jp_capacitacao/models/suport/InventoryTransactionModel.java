package project.yara_silva.Jp_capacitacao.models.suport;

import jakarta.persistence.*;
import lombok.Getter;
import project.yara_silva.Jp_capacitacao.enums.InventoryReasonEnum;
import project.yara_silva.Jp_capacitacao.models.main.OrderModel;
import project.yara_silva.Jp_capacitacao.models.main.ProductModel;
import project.yara_silva.Jp_capacitacao.models.main.UserModel;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Entity
@Table(name = "tb_inventory_transaction")
public class InventoryTransactionModel implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "product_id", nullable = false)
    private ProductModel product;

    @Column(nullable = false)
    private Integer delta;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private InventoryReasonEnum reason;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private OrderModel order;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private UserModel createdBy;

    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    public InventoryTransactionModel(ProductModel product, Integer delta, InventoryReasonEnum reason, OrderModel order, UserModel createdBy) {
        this.product = product;
        this.delta = delta;
        this.reason = reason;
        this.order = order;
        this.createdBy = createdBy;
    }

    public InventoryTransactionModel() {
    }
}
