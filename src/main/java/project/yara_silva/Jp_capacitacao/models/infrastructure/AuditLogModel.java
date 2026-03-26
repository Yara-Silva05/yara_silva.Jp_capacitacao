package project.yara_silva.Jp_capacitacao.models.infrastructure;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import project.yara_silva.Jp_capacitacao.enums.AuditActionEnum;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
@Table(name = "tb_audit_log")
public class AuditLogModel implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String entityType;

    @Column(nullable = false)
    private String entityId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AuditActionEnum action;

    @Column
    private String beforeJson;

    @Column
    private String afterJson;

    @Column
    private String who;

    @Column(nullable = false)
    private LocalDateTime when;
}
