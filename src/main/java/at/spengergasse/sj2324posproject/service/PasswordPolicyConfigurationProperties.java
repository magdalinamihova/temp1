package at.spengergasse.sj2324posproject.service;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "password.policy")
public record PasswordPolicyConfigurationProperties(
    int minLength,
    int maxLength,
    String pattern) {

}
