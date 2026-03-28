package project.yara_silva.Jp_capacitacao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.yara_silva.Jp_capacitacao.models.suport.InventoryTransactionModel;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface InventoryTransactionRepository extends JpaRepository<InventoryTransactionModel, UUID> {
    Optional<List<InventoryTransactionModel>> findByProductId(UUID productId);
}
