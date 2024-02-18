package at.spengergasse.sj2324posproject.service;

public class PasswordPolicyViolationException extends RuntimeException {
    public PasswordPolicyViolationException(String password) {

    }

    public static PasswordPolicyViolationException notProvided() {
        String message = "No password provided, was null, empty or blank!";
        return new PasswordPolicyViolationException(message);
    }

    public static PasswordPolicyViolationException forWrongLength(int length, int minLength, int maxLength) {
        String message = "Actual password has length of %d, but should be between %d and %d!".formatted(length, minLength, maxLength);
        return new PasswordPolicyViolationException(message);
    }
}
