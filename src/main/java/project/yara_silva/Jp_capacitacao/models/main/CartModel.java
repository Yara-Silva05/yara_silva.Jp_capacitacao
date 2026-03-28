package project.yara_silva.Jp_capacitacao.models.main;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
@Table(name = "tb_cart")
public class CartModel implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToOne
    @JoinColumn(name = "user_id",  nullable = false)
    private UserModel user;

    @Column
    private Boolean status;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
    private List<CartItemModel> items = new ArrayList<>();

    public void addCartItem(CartItemModel item) {
        items.add(item);
    }

    public void removeCartItem(CartItemModel item) {
        items.remove(item);
    }

    public void setUser(UserModel user) {
        this.user = user;
    }
}
