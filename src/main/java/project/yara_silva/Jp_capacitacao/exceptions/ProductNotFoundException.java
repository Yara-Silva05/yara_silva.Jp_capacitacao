package project.yara_silva.Jp_capacitacao.exceptions;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException() {
        super("ID do produto não encontrado");
    }
}
