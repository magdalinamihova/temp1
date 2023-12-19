package at.spengergasse.sj2324posproject.persistance.converters;

import at.spengergasse.sj2324posproject.domain.enums.BookStatus;
import at.spengergasse.sj2324posproject.persistance.exception.DataQualityException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BookStatusConverterTest {
    private BookStatusConverter converter;

    @BeforeEach
    void setup() {
        converter = new BookStatusConverter();
    }

    @Test
    void ensureConvertToDBColumnWorksValidWorks() {
        // expect
        assertThat(converter.convertToDatabaseColumn(BookStatus.AVAILABLE)).isEqualTo("AVL");
        assertThat(converter.convertToDatabaseColumn(BookStatus.BORROWED)).isEqualTo("BRW");
        assertThat(converter.convertToDatabaseColumn(BookStatus.MUST_BE_RETURNED)).isEqualTo("MBR");
        assertThat(converter.convertToDatabaseColumn(null)).isNull();
    }

    @Test
    void ensureConvertToEntityAttributeValidWorks() {
        // expect
        assertThat(converter.convertToEntityAttribute("AVL")).isEqualTo(BookStatus.AVAILABLE);
        assertThat(converter.convertToEntityAttribute("BRW")).isEqualTo(BookStatus.BORROWED);
        assertThat(converter.convertToEntityAttribute("MBR")).isEqualTo(BookStatus.MUST_BE_RETURNED);
        assertThat(converter.convertToEntityAttribute(null)).isEqualTo(null);
    }

    @Test
    void ensureConvertToEntityAttributeInValidThrowsException() {
        // expect
        var iaEx = assertThrows(DataQualityException.class, () -> converter.convertToEntityAttribute("INVALID_STATUS"));
        assertThat(iaEx).hasMessage("INVALID_STATUS is not a valid value for BookStatus");
    }
}
