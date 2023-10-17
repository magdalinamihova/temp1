package at.spengergasse.sj2324posproject.persistance.converters;

import at.spengergasse.sj2324posproject.domain.Gender;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Optional;

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
                        case "dk" -> Gender.UNKNOWN;
                        case "other" -> Gender.OTHER;
                        default ->
                                throw new IllegalArgumentException("%s is not a valid value for Gender".formatted(v));
                    };
                }).orElse(null);

    }
}
