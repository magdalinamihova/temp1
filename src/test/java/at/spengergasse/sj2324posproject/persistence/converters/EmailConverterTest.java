package at.spengergasse.sj2324posproject.persistence.converters;

import at.spengergasse.sj2324posproject.domain.records.Email;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class EmailConverterTest {
    private EmailConverter converter;

    @BeforeEach
    void setup(){
        converter=new EmailConverter();
    }

    @Test
    void ensureConvertToDBColumnWorksValidWorks(){
        //expect
        assertThat(converter.convertToDatabaseColumn(Email.of("test@gmail.com"))).isEqualTo("test@gmail.com");
    }
    @Test
    void ensureConvertToEntityAttributeValidWorks(){
        //expect
        assertThat(converter.convertToEntityAttribute("test@gmail.com")).isEqualTo(Email.of("test@gmail.com"));
    }

}
