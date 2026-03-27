package project.yara_silva.Jp_capacitacao.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.yara_silva.Jp_capacitacao.dtos.request.OrderRequestDTO;
import project.yara_silva.Jp_capacitacao.dtos.response.OrderItemResponseDTO;
import project.yara_silva.Jp_capacitacao.dtos.response.OrderResponseDTO;
import project.yara_silva.Jp_capacitacao.enums.OrderStatusEnum;
import project.yara_silva.Jp_capacitacao.exceptions.EmptyOrderException;
import project.yara_silva.Jp_capacitacao.exceptions.OrderNotFoundException;
import project.yara_silva.Jp_capacitacao.models.main.*;
import project.yara_silva.Jp_capacitacao.repository.OrderItemRepositorry;
import project.yara_silva.Jp_capacitacao.repository.OrderRepository;
import project.yara_silva.Jp_capacitacao.repository.UserRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepositorry orderItemRepositorry;

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductService productService;

    @Transactional
    public OrderResponseDTO createOrder(OrderRequestDTO body) {
        UserModel userFromToken = authenticationService.extractUser();
        UserModel user = userRepository.findById(userFromToken.getId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        CartModel cart = user.getCart();

        if (cart.getItems().isEmpty()) {
            throw new EmptyOrderException();
        }

        OrderModel order = new OrderModel(user, body.address(), body.freight(), OrderStatusEnum.CREATED);
        List<OrderItemModel> items = cart.getItems().stream()
                .map(item -> new OrderItemModel(
                        order,
                        item.getProduct(),
                        item.getQuantity(),
                        item.getProduct().getPrice()
                ))
                .toList();

        items.forEach(order::addOrderItem);

        order.setTotal(
                items.stream()
                        .map(item -> item.getPriceSnapshot().multiply(BigDecimal.valueOf(item.getQuantity())))
                        .reduce(BigDecimal.ZERO, BigDecimal::add)
        );

        orderRepository.save(order);
        cart.getItems().clear();

        userRepository.save(user);

        List<OrderItemResponseDTO> itemsDTO = convertOrderItemsToResponseDTO(order);

        return new OrderResponseDTO(user.getId(), order.getAddress(), order.getFreight(), order.getTotal(), order.getStatus(), itemsDTO);
    }

    public OrderResponseDTO getOrder(UUID id) {

        OrderModel order = orderRepository.findById(id).orElseThrow(OrderNotFoundException::new);

        List<OrderItemResponseDTO> itemsDTO = convertOrderItemsToResponseDTO(order);

        return new OrderResponseDTO(order.getUser().getId(), order.getAddress(), order.getFreight(), order.getTotal(),order.getStatus(), itemsDTO);
    }

    @Transactional
    public void cancelOrder(UUID id) {

        OrderModel order = orderRepository.findById(id)
                .orElseThrow(OrderNotFoundException::new);

        if (order.getStatus() != OrderStatusEnum.CREATED && order.getStatus() != OrderStatusEnum.PAID) {
            throw new RuntimeException("Pedido não pode ser cancelado");
        }

        order.setStatus(OrderStatusEnum.CANCELED);
        orderRepository.save(order);
    }


    private List<OrderItemResponseDTO> convertOrderItemsToResponseDTO(OrderModel order) {
        return order.getItems().stream()
                .map(item -> new OrderItemResponseDTO(
                        order.getId(),
                        productService.convertProductSimpleToResponseDTO(item.getProduct()),
                        item.getQuantity(),
                        item.getPriceSnapshot()
                ))
                .toList();
    }

    private OrderItemResponseDTO convertOrderItemToResponseDTO(OrderItemModel orderItem) {
        return new OrderItemResponseDTO(
                orderItem.getOrder().getId(),
                productService.convertProductSimpleToResponseDTO(orderItem.getProduct()),
                orderItem.getQuantity(),
                orderItem.getPriceSnapshot()
        );
    }
}
