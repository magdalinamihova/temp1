package at.spengergasse.sj2324posproject.domain.records;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;

public record Address(
        @NotBlank @NotEmpty String street,
        @NotBlank @NotEmpty String city,
        @NotBlank @NotEmpty String postalCode,
        @NotBlank @NotEmpty String country
) {
    public Address {
        Objects.requireNonNull(street);
        Objects.requireNonNull(city);
        Objects.requireNonNull(postalCode);
        Objects.requireNonNull(country);
    }

    public static Address of(String street, String city, String postalCode, String country) {
        return new Address(street, city, postalCode, country);
    }
}

