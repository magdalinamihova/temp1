package at.spengergasse.sj2324posproject.persistence.converters;

import at.spengergasse.sj2324posproject.domain.enums.Gender;
import at.spengergasse.sj2324posproject.persistence.exception.DataQualityException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class GenderConverterTest {
    private GenderConverter converter;

    @BeforeEach
    void setup(){
        converter=new GenderConverter();
    }

    @Test
    void ensureConvertToDBColumnWorksValidWorks(){
        //expect
        assertThat(converter.convertToDatabaseColumn(Gender.FEMALE)).isEqualTo("f");
        assertThat(converter.convertToDatabaseColumn(Gender.MALE)).isEqualTo("m");
        assertThat(converter.convertToDatabaseColumn(Gender.OTHER)).isEqualTo("o");
        assertThat(converter.convertToDatabaseColumn(Gender.UNKNOWN)).isEqualTo("u");
        assertThat(converter.convertToDatabaseColumn(null)).isNull();
    }
    @Test
    void ensureConvertToEntityAttributeValidWorks(){
        //expect
        assertThat(converter.convertToEntityAttribute("f")).isEqualTo(Gender.FEMALE);
        assertThat(converter.convertToEntityAttribute("m")).isEqualTo(Gender.MALE);
        assertThat(converter.convertToEntityAttribute("o")).isEqualTo(Gender.OTHER);
        assertThat(converter.convertToEntityAttribute("u")).isEqualTo(Gender.UNKNOWN);
        assertThat(converter.convertToEntityAttribute(null)).isEqualTo(null);
    }
    @Test
    void ensureConvertToEntityAttributeInValidThrowsException(){
        //expect
       var iaEx = assertThrows(DataQualityException.class,()->converter.convertToEntityAttribute("k"));
       assertThat(iaEx).hasMessage("k is not a valid value for Gender");
    }
}