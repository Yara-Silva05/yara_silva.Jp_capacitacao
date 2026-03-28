package project.yara_silva.Jp_capacitacao.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.yara_silva.Jp_capacitacao.dtos.response.InventoryTransactionResponseDTO;
import project.yara_silva.Jp_capacitacao.enums.InventoryReasonEnum;
import project.yara_silva.Jp_capacitacao.exceptions.ProductNotFoundException;
import project.yara_silva.Jp_capacitacao.models.main.OrderItemModel;
import project.yara_silva.Jp_capacitacao.models.main.OrderModel;
import project.yara_silva.Jp_capacitacao.models.main.ProductModel;
import project.yara_silva.Jp_capacitacao.models.main.UserModel;
import project.yara_silva.Jp_capacitacao.models.suport.InventoryTransactionModel;
import project.yara_silva.Jp_capacitacao.repository.InventoryTransactionRepository;
import java.util.List;
import java.util.UUID;

@Service
public class InventoryTransactionService {

    @Autowired
    private InventoryTransactionRepository inventoryTransactionRepository;

    @Autowired
    private ProductService productService;

    public List<InventoryTransactionResponseDTO> getAllinventoryTransactions() {
        List<InventoryTransactionModel> logs = inventoryTransactionRepository.findAll();

        return logs.stream().map(this::convertLogsToResponseDTO).toList();
    }

    public List<InventoryTransactionResponseDTO> getByProductId(UUID id) {
        List<InventoryTransactionModel> logs = inventoryTransactionRepository.findByProductId(id).orElseThrow(ProductNotFoundException::new);

        return logs.stream().map(this::convertLogsToResponseDTO).toList();
    }

    public void createLog (ProductModel product, OrderModel order, OrderItemModel item, UserModel user, InventoryReasonEnum reason) {
        InventoryTransactionModel log = new InventoryTransactionModel(product, item.getQuantity() ,reason, order, user);

        inventoryTransactionRepository.save(log);
    }

    private InventoryTransactionResponseDTO convertLogsToResponseDTO(InventoryTransactionModel log) {
        return new InventoryTransactionResponseDTO(
                log.getId(),
                productService.getByIdSimple(log.getProduct().getId()),
                log.getDelta(),
                log.getReason(),
                log.getOrder().getId(),
                log.getCreatedBy().getId(),
                log.getCreatedAt()
        );
    }
}
