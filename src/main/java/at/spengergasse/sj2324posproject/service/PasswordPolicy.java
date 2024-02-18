package at.spengergasse.sj2324posproject.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PasswordPolicy {

    @Value("${password.policy.maxLength}")
    private int maxLength;

    @Value("${password.policy.minLength}")
    private int minLength;

    @Value("${password.policy.pattern.regexp:^[A-Z]{16,100}$}")
    private String pattern;

    public void complies(String password) {
        if (password == null || password.isBlank()) throw PasswordPolicyViolationException.notProvided();
        if (password.length() < minLength || password.length() > maxLength) throw PasswordPolicyViolationException.forWrongLength(password.length(), minLength, maxLength);
    }

    public String random() {
        return ""; // fake it till you make it
    }
}
