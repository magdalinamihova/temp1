package at.spengergasse.sj2324posproject.domain.records;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.util.Objects;

public record PhoneNumber(
        @NotBlank @Pattern(regexp = "\\+43\\d{1,14}", message = "Invalid phone number format") String value
) {
    public PhoneNumber {
        Objects.requireNonNull(value);
    }

    public static PhoneNumber of(String value) {
        return new PhoneNumber(value);
    }
}
