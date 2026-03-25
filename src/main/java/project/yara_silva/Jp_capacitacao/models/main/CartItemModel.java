package project.yara_silva.Jp_capacitacao.models.main;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "TB_CART_ITEM")
public class CartItemModel implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;

    @ManyToOne
    @JoinColumn(name = "cart_id", nullable = false)
    private CartModel cart;

    /*
    @Column(nullable = false)
    private ProductModel product;
     */

    @Column(nullable = false)
    private Integer quantity;
}
