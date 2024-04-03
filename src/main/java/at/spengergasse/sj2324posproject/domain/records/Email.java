package at.spengergasse.sj2324posproject.domain.records;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import java.util.Objects;
import java.util.regex.Pattern;
import java.util.function.Predicate;

@Embeddable
public class Email {

    private static final int length = 64;

    private static final Pattern pattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    private static final Predicate<String> isValidEmail = pattern.asMatchPredicate();

    private String value;

    public Email() {
        // Default constructor required by Hibernate
    }

    public Email(@NotEmpty @NotBlank String value) {
        Objects.requireNonNull(value);
        if (!isValidEmail.test(value)) {
            throw new IllegalArgumentException("Invalid email:" + value);
        }
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static Email of(String value) {
        return new Email(value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Email email = (Email) o;
        return Objects.equals(value, email.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
