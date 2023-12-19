package at.spengergasse.sj2324posproject.persistance.converters;

import at.spengergasse.sj2324posproject.domain.records.Address;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AddressConverterTest {

    private AddressConverter converter;

    @BeforeEach
    void setup() {
        converter = new AddressConverter();
    }

    @Test
    void ensureConvertToDBColumnWorksValidWorks() {
        assertThat(converter.convertToDatabaseColumn(Address.of("69 Liesingbachstraße", "Vienna", "1100", "Austria"))).isEqualTo("69 Liesingbachstraße|Vienna|1100|Austria");
    }

    @Test
    void ensureConvertToEntityAttributeValidWorks() {
       assertThat(converter.convertToEntityAttribute("69 Liesingbachstraße|Vienna|1100|Austria")).isEqualTo(Address.of("69 Liesingbachstraße", "Vienna", "1100", "Austria"));
    }

    @Test
    void ensureConvertToEntityAttributeInvalidValue() {
        String invalidDbValue = "InvalidValue";
        assertThrows(IllegalArgumentException.class, () -> converter.convertToEntityAttribute(invalidDbValue));
    }

    @Test
    void ensureConvertToEntityAttributeNullValue() {
        assertThat(converter.convertToEntityAttribute(null)).isNull();
    }

    @Test
    void ensureConvertToDBColumnNullValue() {
        assertThat( converter.convertToDatabaseColumn(null)).isNull();
    }

}
