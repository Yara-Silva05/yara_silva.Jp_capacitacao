package project.yara_silva.Jp_capacitacao.exceptions;

public class EmptyOrderException extends RuntimeException {
    public EmptyOrderException() {
        super("O pedido está vazio.");
    }
}
