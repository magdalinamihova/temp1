package at.spengergasse.sj2324posproject.persistence.converters;

import at.spengergasse.sj2324posproject.domain.enums.RequestStatus;
import at.spengergasse.sj2324posproject.persistence.exception.DataQualityException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RequestStatusConverterTest {
    private RequestStatusConverter converter;

    @BeforeEach
    void setup() {
        converter = new RequestStatusConverter();
    }

    @Test
    void ensureConvertToDBColumnWorksValidWorks() {
        // expect
        assertThat(converter.convertToDatabaseColumn(RequestStatus.PENDING)).isEqualTo("P");
        assertThat(converter.convertToDatabaseColumn(RequestStatus.APPROVED)).isEqualTo("A");
        assertThat(converter.convertToDatabaseColumn(RequestStatus.REJECTED)).isEqualTo("R");
        assertThat(converter.convertToDatabaseColumn(null)).isNull();
    }

    @Test
    void ensureConvertToEntityAttributeValidWorks() {
        // expect
        assertThat(converter.convertToEntityAttribute("P")).isEqualTo(RequestStatus.PENDING);
        assertThat(converter.convertToEntityAttribute("A")).isEqualTo(RequestStatus.APPROVED);
        assertThat(converter.convertToEntityAttribute("R")).isEqualTo(RequestStatus.REJECTED);
        assertThat(converter.convertToEntityAttribute(null)).isEqualTo(null);
    }

    @Test
    void ensureConvertToEntityAttributeInValidThrowsException() {
        // expect
        var iaEx = assertThrows(DataQualityException.class, () -> converter.convertToEntityAttribute("INVALID_STATUS"));
        assertThat(iaEx).hasMessage("INVALID_STATUS is not a valid value for RequestStatus");
    }
}
