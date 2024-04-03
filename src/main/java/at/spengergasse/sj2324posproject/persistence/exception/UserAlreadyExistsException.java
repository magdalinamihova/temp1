package at.spengergasse.sj2324posproject.persistence.exception;

public class UserAlreadyExistsException extends RuntimeException {
    private final String username;
    public UserAlreadyExistsException(String username) {
        this.username = username;
    }
}
