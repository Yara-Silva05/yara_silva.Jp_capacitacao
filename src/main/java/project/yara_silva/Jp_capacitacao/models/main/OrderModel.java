package project.yara_silva.Jp_capacitacao.models.main;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import project.yara_silva.Jp_capacitacao.enums.OrderStatusEnum;
import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "tb_order")
public class OrderModel implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private UserModel user;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItemModel> items = new ArrayList<>();

    @Column(nullable = false)
    private String address;

    @Column
    private BigDecimal freight;

    @Column
    private BigDecimal discount;

    @Column(nullable = false)
    private BigDecimal total;

    @Column
    private LocalDateTime createdAt = LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrderStatusEnum status;

    public OrderModel(UserModel user, String address, BigDecimal freight, OrderStatusEnum status) {
        this.user = user;
        this.address = address;
        this.freight = freight;
        this.status = status;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public void setStatus(OrderStatusEnum status) {
        this.status = status;
    }

    public void addOrderItem(OrderItemModel item) {
        items.add(item);
    }
}
