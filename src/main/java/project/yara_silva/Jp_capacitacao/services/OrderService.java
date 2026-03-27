package project.yara_silva.Jp_capacitacao.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.yara_silva.Jp_capacitacao.dtos.request.OrderItemRequestDTO;
import project.yara_silva.Jp_capacitacao.dtos.request.OrderRequestDTO;
import project.yara_silva.Jp_capacitacao.dtos.response.OrderItemResponseDTO;
import project.yara_silva.Jp_capacitacao.dtos.response.OrderResponseDTO;
import project.yara_silva.Jp_capacitacao.dtos.response.ProductSimpleResponseDTO;
import project.yara_silva.Jp_capacitacao.enums.OrderStatusEnum;
import project.yara_silva.Jp_capacitacao.exceptions.EmptyOrderException;
import project.yara_silva.Jp_capacitacao.models.main.CartModel;
import project.yara_silva.Jp_capacitacao.models.main.OrderItemModel;
import project.yara_silva.Jp_capacitacao.models.main.OrderModel;
import project.yara_silva.Jp_capacitacao.models.main.UserModel;
import project.yara_silva.Jp_capacitacao.repository.OrderItemRepositorry;
import project.yara_silva.Jp_capacitacao.repository.OrderRepository;
import project.yara_silva.Jp_capacitacao.repository.UserRepository;

import java.math.BigDecimal;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderItemRepositorry orderItemRepositorry;

    @Autowired
    AuthenticationService authenticationService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProductService productService;

    @Transactional
    public OrderResponseDTO createOrderItem(OrderRequestDTO body) {
        UserModel user = authenticationService.extractUser();
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

        List<OrderItemResponseDTO> itemsDTO = order.getItems().stream()
                .map(item-> new OrderItemResponseDTO(order.getId(), productService.convertProductSimpleToResponseDTO(item.getProduct()), item.getQuantity(), item.getPriceSnapshot()))
                .toList();

        return new OrderResponseDTO(user.getId(), order.getAddress(), order.getFreight(), order.getTotal(),order.getStatus(), itemsDTO);
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
