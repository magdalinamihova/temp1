package at.spengergasse.sj2324posproject.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class PasswordPolicy {

    private final PasswordPolicyConfigurationProperties passwordPolicy;

    /*@Value("${password.policy.maxLength}")
    private int maxLength;

    @Value("${password.policy.minLength}")
    private int minLength;

    @Value("${password.policy.pattern.regexp:^[A-Z]{16,100}$}")
    private String pattern;*/

    public void complies(String password) {
        if (password == null || password.isBlank()) throw PasswordPolicyViolationException.notProvided();
        if (password.length() < passwordPolicy.minLength() || password.length() > passwordPolicy.maxLength())
            throw PasswordPolicyViolationException.forWrongLength(password.length(), passwordPolicy.minLength() , passwordPolicy.maxLength());
    }

    public String random() {
        return ""; // fake it till you make it
    }
}
