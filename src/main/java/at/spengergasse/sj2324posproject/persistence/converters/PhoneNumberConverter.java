package at.spengergasse.sj2324posproject.persistence.converters;

import at.spengergasse.sj2324posproject.domain.records.PhoneNumber;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import java.util.Optional;

@Converter(autoApply = true)
public class PhoneNumberConverter implements AttributeConverter<PhoneNumber, String> {

    @Override
    public String convertToDatabaseColumn(PhoneNumber phoneNumber) {
        return Optional.ofNullable(phoneNumber).map(PhoneNumber::getValue).orElse(null);
    }

    @Override
    public PhoneNumber convertToEntityAttribute(String dbValue) {
        return Optional.ofNullable(dbValue)
                .filter(value -> value.matches("\\+43\\d{1,14}"))
                .map(PhoneNumber::new)
                .orElseThrow(() -> new IllegalArgumentException("Invalid phone number format: " + dbValue));
    }
}
