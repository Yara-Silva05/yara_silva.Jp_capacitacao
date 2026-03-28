package project.yara_silva.Jp_capacitacao.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.yara_silva.Jp_capacitacao.dtos.request.CartItemRequestDTO;
import project.yara_silva.Jp_capacitacao.dtos.request.UpdateCartItemRequestDTO;
import project.yara_silva.Jp_capacitacao.dtos.response.CartItemResponseDTO;
import project.yara_silva.Jp_capacitacao.exceptions.CartItemAlreadyExistsException;
import project.yara_silva.Jp_capacitacao.exceptions.CartItemNotFoundException;
import project.yara_silva.Jp_capacitacao.exceptions.ProductNotFoundException;
import project.yara_silva.Jp_capacitacao.models.main.CartItemModel;
import project.yara_silva.Jp_capacitacao.models.main.CartModel;
import project.yara_silva.Jp_capacitacao.models.main.UserModel;
import project.yara_silva.Jp_capacitacao.repository.CartItemRepository;
import project.yara_silva.Jp_capacitacao.repository.CartRepository;
import project.yara_silva.Jp_capacitacao.repository.ProductRepository;
import project.yara_silva.Jp_capacitacao.repository.UserRepository;
import java.util.List;
import java.util.UUID;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationService authenticationService;

    @Transactional
    public CartItemResponseDTO createCartItem(CartItemRequestDTO body) {
        UserModel userFromToken = authenticationService.extractUser();
        UserModel user = userRepository.findById(userFromToken.getId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        CartModel cart = user.getCart();

        CartItemModel item = new CartItemModel(cart, productRepository.findById(body.idProduct()).orElseThrow(ProductNotFoundException::new), body.quantity());

        if(cart.getItems().stream()
                .anyMatch(cartItem -> cartItem.equals(item))) {
            throw new CartItemAlreadyExistsException();
        }

        cart.addCartItem(item);
        userRepository.save(user);

        return convertCartItemToResponseDTO(item);
    }

    @Transactional
    public List<CartItemResponseDTO> getAllCartItem() {
        UserModel userFromToken = authenticationService.extractUser();
        UserModel user = userRepository.findById(userFromToken.getId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        CartModel cart = user.getCart();
        List<CartItemModel> items = cart.getItems();

        return items.stream()
                .map(this::convertCartItemToResponseDTO)
                .toList();
    }

    @Transactional
    public CartItemResponseDTO updateCartItem(UUID id, UpdateCartItemRequestDTO body) {

        CartItemModel item = cartItemRepository.findById(id)
                .orElseThrow(CartItemNotFoundException::new);

        if (body.quantity() != null) {
            if (body.quantity() >= 1) {
                item.setQuantity(body.quantity());
            }
        }
        cartItemRepository.save(item);
        return convertCartItemToResponseDTO(item);
    }

    @Transactional
    public void deleteCartItem(UUID id) {
        cartItemRepository.deleteById(id);
    }

    private CartItemResponseDTO convertCartItemToResponseDTO(CartItemModel cartItem) {
        return new CartItemResponseDTO(
                cartItem.getProduct().getId(),
                cartItem.getQuantity()
        );
    }
}
