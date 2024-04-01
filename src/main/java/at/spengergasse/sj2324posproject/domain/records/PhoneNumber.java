package at.spengergasse.sj2324posproject.domain.records;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import java.util.Objects;

@Embeddable
public class PhoneNumber {

    @NotBlank
    @Pattern(regexp = "\\+43\\d{1,14}", message = "Invalid phone number format")
    private String value;

    public PhoneNumber() {
        // Default constructor required by Hibernate
    }

    public PhoneNumber(String value) {
        this.value = Objects.requireNonNull(value);
    }

    public String getValue() {
        return value;
    }

    public static PhoneNumber of(String value) {
        return new PhoneNumber(value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PhoneNumber that = (PhoneNumber) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
