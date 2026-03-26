package project.yara_silva.Jp_capacitacao.models.suport;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import project.yara_silva.Jp_capacitacao.models.main.ProductModel;
import project.yara_silva.Jp_capacitacao.models.main.UserModel;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
@Table(name = "tb_reviews")
public class ReviewModel implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "product_id", nullable = false)
    private ProductModel product;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private UserModel user;

    @Column(nullable = false)
    private Double rating;

    @Column
    private String userComment;

    @Column(nullable = false)
    private LocalDateTime createdAt;
}
