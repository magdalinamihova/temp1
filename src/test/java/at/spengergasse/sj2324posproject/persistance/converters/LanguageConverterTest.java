package at.spengergasse.sj2324posproject.persistance.converters;
import at.spengergasse.sj2324posproject.domain.enums.Language;
import at.spengergasse.sj2324posproject.persistance.exception.DataQualityException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
class LanguageConverterTest {

    private LanguageConverter converter;

    @BeforeEach
    void setup() {
        converter = new LanguageConverter();
    }

    @Test
    void ensureConvertToDBColumnWorksValidWorks() {
        // expect
        assertThat(converter.convertToDatabaseColumn(Language.ENGLISH)).isEqualTo("EN");
        assertThat(converter.convertToDatabaseColumn(Language.GERMAN)).isEqualTo("DE");
        // should work for all languages
        assertThat(converter.convertToDatabaseColumn(null)).isNull();
    }

    @Test
    void ensureConvertToEntityAttributeValidWorks() {
        // expect
        assertThat(converter.convertToEntityAttribute("EN")).isEqualTo(Language.ENGLISH);
        assertThat(converter.convertToEntityAttribute("DE")).isEqualTo(Language.GERMAN);
        // Add assertions for other languages as needed
        assertThat(converter.convertToEntityAttribute(null)).isEqualTo(null);
    }

    @Test
    void ensureConvertToEntityAttributeInValidThrowsException() {
        // expect
        var iaEx = assertThrows(DataQualityException.class, () -> converter.convertToEntityAttribute("INVALID_LANGUAGE"));
        assertThat(iaEx).hasMessage("INVALID_LANGUAGE is not a valid value for Language");
    }
}