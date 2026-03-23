package project.yara_silva.Jp_capacitacao.exceptions;

public class UserExistsException extends RuntimeException{
    public UserExistsException() {
        super("Email já cadastrado.");
    }
}
