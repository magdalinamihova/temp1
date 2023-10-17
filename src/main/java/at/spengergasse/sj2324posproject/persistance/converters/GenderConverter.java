package at.spengergasse.sj2324posproject.persistance.converters;

import at.spengergasse.sj2324posproject.domain.enums.Gender;
import at.spengergasse.sj2324posproject.persistance.exception.DataQualityException;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Optional;
import java.util.zip.DataFormatException;

@Converter(autoApply = true)
public class GenderConverter implements AttributeConverter<Gender,String> {

    @Override
    public String convertToDatabaseColumn(Gender gender) {
        return Optional.ofNullable(gender)
                .map(g -> switch (g) {
                    case MALE -> "m";
                    case FEMALE -> "f";
                    case UNKNOWN -> "u";
                    case OTHER -> "o";
                }).orElse(null);
    }

    @Override
    public Gender convertToEntityAttribute(String dbValue) {
        return Optional.ofNullable(dbValue)
                .map(v -> {
                    return switch (v) {
                        case "m" -> Gender.MALE;
                        case "f" -> Gender.FEMALE;
                        case "u" -> Gender.UNKNOWN;
                        case "o" -> Gender.OTHER;
                        default ->
                                throw DataQualityException.forInvalidValue(dbValue,Gender.class);
                    };
                }).orElse(null);

    }
}
