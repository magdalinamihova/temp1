package at.spengergasse.sj2324posproject.persistance.converters;

import at.spengergasse.sj2324posproject.domain.enums.RequestStatus;
import at.spengergasse.sj2324posproject.persistance.exception.DataQualityException;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Optional;

@Converter(autoApply = true)
public class RequestStatusConverter implements AttributeConverter<RequestStatus, String> {

    @Override
    public String convertToDatabaseColumn(RequestStatus requestStatus) {
        return Optional.ofNullable(requestStatus)
                .map(rs -> switch (rs) {
                    case PENDING -> "P";
                    case APPROVED -> "A";
                    case REJECTED -> "R";
                }).orElse(null);
    }

    @Override
    public RequestStatus convertToEntityAttribute(String dbValue) {
        return Optional.ofNullable(dbValue)
                .map(v -> {
                    return switch (v) {
                        case "P" -> RequestStatus.PENDING;
                        case "A" -> RequestStatus.APPROVED;
                        case "R" -> RequestStatus.REJECTED;
                        default ->
                                throw DataQualityException.forInvalidValue(dbValue, RequestStatus.class);
                    };
                }).orElse(null);
    }
}

