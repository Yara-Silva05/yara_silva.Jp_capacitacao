package project.yara_silva.Jp_capacitacao.exceptions;

public class UserAlreadyExistsException extends RuntimeException{
    public UserAlreadyExistsException() {
        super("Email já cadastrado.");
    }
}
