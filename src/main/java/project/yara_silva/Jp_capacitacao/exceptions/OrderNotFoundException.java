package project.yara_silva.Jp_capacitacao.exceptions;

public class OrderNotFoundException extends RuntimeException {
    public OrderNotFoundException() {
        super("Carrinho não encontrado.");
    }
}
