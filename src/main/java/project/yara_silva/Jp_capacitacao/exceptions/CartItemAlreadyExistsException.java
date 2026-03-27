package project.yara_silva.Jp_capacitacao.exceptions;

public class CartItemAlreadyExistsException extends RuntimeException {
    public CartItemAlreadyExistsException() {
        super("O carrinho já existe");
    }
}
