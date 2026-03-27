package project.yara_silva.Jp_capacitacao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.yara_silva.Jp_capacitacao.models.main.OrderModel;
import java.util.UUID;

@Repository
public interface OrderRepository  extends JpaRepository<OrderModel, UUID> {
}
