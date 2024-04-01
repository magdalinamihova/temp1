package at.spengergasse.sj2324posproject.persistence.converters;

import at.spengergasse.sj2324posproject.domain.records.Address;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import java.util.Objects;

@Converter(autoApply = true)
public class AddressConverter implements AttributeConverter<Address, String> {

    private static final String COMPONENT_SEPARATOR = "|";

    @Override
    public String convertToDatabaseColumn(Address address) {
        if (address == null) {
            return null;
        }

        return address.getStreet() + COMPONENT_SEPARATOR +
                address.getCity() + COMPONENT_SEPARATOR +
                address.getPostalCode() + COMPONENT_SEPARATOR +
                address.getCountry();
    }

    @Override
    public Address convertToEntityAttribute(String dbValue) {
        if (dbValue == null) {
            return null;
        }

        String[] components = dbValue.split("\\|");

        if (components.length != 4) {
            throw new IllegalArgumentException("Invalid database value for Address: " + dbValue);
        }

        return new Address(
                Objects.requireNonNull(components[0]),
                Objects.requireNonNull(components[1]),
                Objects.requireNonNull(components[2]),
                Objects.requireNonNull(components[3])
        );
    }
}
