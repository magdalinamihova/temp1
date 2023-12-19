package at.spengergasse.sj2324posproject.persistance.converters;

import at.spengergasse.sj2324posproject.domain.records.PhoneNumber;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PhoneNumberConverterTest {

    private PhoneNumberConverter converter;

    @BeforeEach
    void setup() {
        converter = new PhoneNumberConverter();
    }

    @Test
    void ensureConvertToDBColumnWorksValidWorks() {
        assertThat(converter.convertToDatabaseColumn(PhoneNumber.of("+43123456789"))).isEqualTo("+43123456789");
    }

    @Test
    void ensureConvertToEntityAttributeValidWorks() {
        assertThat(converter.convertToEntityAttribute("+43123456789")).isEqualTo(PhoneNumber.of("+43123456789"));
    }

    @Test
    void ensureConvertToEntityAttributeInvalidValue() {
        String invalidDbValue = "InvalidValue";
        assertThrows(IllegalArgumentException.class, () -> converter.convertToEntityAttribute(invalidDbValue));
    }


    @Test
    void ensureConvertToEntityAttributeInvalidFormat() {
        assertThrows(IllegalArgumentException.class, () -> converter.convertToEntityAttribute("+4312abc3456789"));
    }
}
