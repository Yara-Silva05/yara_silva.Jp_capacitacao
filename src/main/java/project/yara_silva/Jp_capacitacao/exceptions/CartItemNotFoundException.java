package project.yara_silva.Jp_capacitacao.exceptions;

public class CartItemNotFoundException extends RuntimeException {
    public CartItemNotFoundException() {
        super("Carrinho não encontrado.");
    }
}
