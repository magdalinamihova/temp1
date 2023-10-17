package at.spengergasse.sj2324posproject.persistance.converters;

import at.spengergasse.sj2324posproject.domain.enums.UserRole;
import at.spengergasse.sj2324posproject.persistance.exception.DataQualityException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class UserRoleConverterTest {
    private UserRoleConverter converter;

    @BeforeEach
    void setup() {
        converter = new UserRoleConverter();
    }

    @Test
    void ensureConvertToDBColumnWorksValidWorks() {
        // expect
        assertThat(converter.convertToDatabaseColumn(UserRole.ADMIN)).isEqualTo("A");
        assertThat(converter.convertToDatabaseColumn(UserRole.STANDARD)).isEqualTo("S");
        assertThat(converter.convertToDatabaseColumn(null)).isNull();
    }

    @Test
    void ensureConvertToEntityAttributeValidWorks() {
        // expect
        assertThat(converter.convertToEntityAttribute("A")).isEqualTo(UserRole.ADMIN);
        assertThat(converter.convertToEntityAttribute("S")).isEqualTo(UserRole.STANDARD);
        assertThat(converter.convertToEntityAttribute(null)).isEqualTo(null);
    }

    @Test
    void ensureConvertToEntityAttributeInValidThrowsException() {
        // expect
        var iaEx = assertThrows(DataQualityException.class, () -> converter.convertToEntityAttribute("INVALID_ROLE"));
        assertThat(iaEx).hasMessage("INVALID_ROLE is not a valid value for UserRole");
    }
}
