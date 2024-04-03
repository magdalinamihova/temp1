package at.spengergasse.sj2324posproject.domain.records;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public record Email(
        @Column(name="email")
        @NotEmpty @NotBlank String value)  {
    public static final int length = 64;

    public static final Pattern pattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    public static final Predicate<String> isValidEmail = pattern.asMatchPredicate();

    public Email {
        Objects.requireNonNull(value);
        if (!isValidEmail.test(value)) throw new IllegalArgumentException("Invalid email:" + value);
    }

    public static Email of(String value) {return new Email(value);}
}