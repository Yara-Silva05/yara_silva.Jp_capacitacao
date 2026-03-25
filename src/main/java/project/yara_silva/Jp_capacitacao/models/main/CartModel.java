package project.yara_silva.Jp_capacitacao.models.main;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "TB_CART")
public class CartModel implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;

    @OneToOne
    @JoinColumn(name = "user_id")
    private UserModel user;

    @Column
    private Boolean status;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
    private List<CartItemModel> items = new ArrayList<>();
}
