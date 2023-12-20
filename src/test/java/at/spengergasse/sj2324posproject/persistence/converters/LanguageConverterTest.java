package at.spengergasse.sj2324posproject.persistence.converters;
import at.spengergasse.sj2324posproject.domain.enums.Language;
import at.spengergasse.sj2324posproject.persistence.exception.DataQualityException;
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
        assertThat(converter.convertToDatabaseColumn(Language.FRENCH)).isEqualTo("FR");
        assertThat(converter.convertToDatabaseColumn(Language.SPANISH)).isEqualTo("ES");
        assertThat(converter.convertToDatabaseColumn(Language.ITALIAN)).isEqualTo("IT");
        assertThat(converter.convertToDatabaseColumn(Language.PORTUGUESE)).isEqualTo("PT");
        assertThat(converter.convertToDatabaseColumn(Language.DUTCH)).isEqualTo("NL");
        assertThat(converter.convertToDatabaseColumn(Language.RUSSIAN)).isEqualTo("RU");
        assertThat(converter.convertToDatabaseColumn(Language.POLISH)).isEqualTo("PL");
        assertThat(converter.convertToDatabaseColumn(Language.SWEDISH)).isEqualTo("SV");
        assertThat(converter.convertToDatabaseColumn(Language.NORWEGIAN)).isEqualTo("NO");
        assertThat(converter.convertToDatabaseColumn(Language.DANISH)).isEqualTo("DA");
        assertThat(converter.convertToDatabaseColumn(Language.FINNISH)).isEqualTo("FI");
        assertThat(converter.convertToDatabaseColumn(Language.GREEK)).isEqualTo("EL");
        assertThat(converter.convertToDatabaseColumn(Language.CZECH)).isEqualTo("CS");
        assertThat(converter.convertToDatabaseColumn(Language.HUNGARIAN)).isEqualTo("HU");
        assertThat(converter.convertToDatabaseColumn(Language.ROMANIAN)).isEqualTo("RO");
        assertThat(converter.convertToDatabaseColumn(Language.BULGARIAN)).isEqualTo("BG");
        assertThat(converter.convertToDatabaseColumn(Language.SERBIAN)).isEqualTo("SR");
        assertThat(converter.convertToDatabaseColumn(Language.CROATIAN)).isEqualTo("HR");
        assertThat(converter.convertToDatabaseColumn(Language.BOSNIAN)).isEqualTo("BS");
        assertThat(converter.convertToDatabaseColumn(Language.SLOVAK)).isEqualTo("SK");
        assertThat(converter.convertToDatabaseColumn(Language.SLOVENIAN)).isEqualTo("SL");
        assertThat(converter.convertToDatabaseColumn(null)).isNull();
    }


    @Test
    void ensureConvertToEntityAttributeValidWorks() {
        // expect
        assertThat(converter.convertToEntityAttribute("EN")).isEqualTo(Language.ENGLISH);
        assertThat(converter.convertToEntityAttribute("DE")).isEqualTo(Language.GERMAN);
        assertThat(converter.convertToEntityAttribute("FR")).isEqualTo(Language.FRENCH);
        assertThat(converter.convertToEntityAttribute("ES")).isEqualTo(Language.SPANISH);
        assertThat(converter.convertToEntityAttribute("IT")).isEqualTo(Language.ITALIAN);
        assertThat(converter.convertToEntityAttribute("PT")).isEqualTo(Language.PORTUGUESE);
        assertThat(converter.convertToEntityAttribute("NL")).isEqualTo(Language.DUTCH);
        assertThat(converter.convertToEntityAttribute("RU")).isEqualTo(Language.RUSSIAN);
        assertThat(converter.convertToEntityAttribute("PL")).isEqualTo(Language.POLISH);
        assertThat(converter.convertToEntityAttribute("SV")).isEqualTo(Language.SWEDISH);
        assertThat(converter.convertToEntityAttribute("NO")).isEqualTo(Language.NORWEGIAN);
        assertThat(converter.convertToEntityAttribute("DA")).isEqualTo(Language.DANISH);
        assertThat(converter.convertToEntityAttribute("FI")).isEqualTo(Language.FINNISH);
        assertThat(converter.convertToEntityAttribute("EL")).isEqualTo(Language.GREEK);
        assertThat(converter.convertToEntityAttribute("CS")).isEqualTo(Language.CZECH);
        assertThat(converter.convertToEntityAttribute("HU")).isEqualTo(Language.HUNGARIAN);
        assertThat(converter.convertToEntityAttribute("RO")).isEqualTo(Language.ROMANIAN);
        assertThat(converter.convertToEntityAttribute("BG")).isEqualTo(Language.BULGARIAN);
        assertThat(converter.convertToEntityAttribute("SR")).isEqualTo(Language.SERBIAN);
        assertThat(converter.convertToEntityAttribute("HR")).isEqualTo(Language.CROATIAN);
        assertThat(converter.convertToEntityAttribute("BS")).isEqualTo(Language.BOSNIAN);
        assertThat(converter.convertToEntityAttribute("SK")).isEqualTo(Language.SLOVAK);
        assertThat(converter.convertToEntityAttribute("SL")).isEqualTo(Language.SLOVENIAN);
        assertThat(converter.convertToEntityAttribute(null)).isEqualTo(null);
    }


    @Test
    void ensureConvertToEntityAttributeInValidThrowsException() {
        // expect
        var iaEx = assertThrows(DataQualityException.class, () -> converter.convertToEntityAttribute("INVALID_LANGUAGE"));
        assertThat(iaEx).hasMessage("INVALID_LANGUAGE is not a valid value for Language");
    }
}