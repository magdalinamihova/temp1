package at.spengergasse.sj2324posproject.persistance.converters;

import at.spengergasse.sj2324posproject.domain.enums.BookStatus;
import at.spengergasse.sj2324posproject.domain.enums.Gender;
import at.spengergasse.sj2324posproject.persistance.exception.DataQualityException;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Optional;

@Converter(autoApply = true)
public class BookStatusConverter implements AttributeConverter<BookStatus,String> {

    @Override
    public String convertToDatabaseColumn(BookStatus bS) {
        return Optional.ofNullable(bS)
                .map(b -> switch (b) {
                    case AVAILABLE -> "AVL";
                    case BORROWED -> "BRW";
                    case MUST_BE_RETURNED -> "MBR";
                }).orElse(null);
    }

    @Override
    public BookStatus convertToEntityAttribute(String dbValue) {
        return Optional.ofNullable(dbValue)
                .map(v -> {
                    return switch (v) {
                        case "AVL" -> BookStatus.AVAILABLE;
                        case "BRW" -> BookStatus.BORROWED;
                        case "MBR" -> BookStatus.MUST_BE_RETURNED;
                        default ->
                            throw DataQualityException.forInvalidValue(dbValue,BookStatus.class);
                    };
                }).orElse(null);
    }
}
