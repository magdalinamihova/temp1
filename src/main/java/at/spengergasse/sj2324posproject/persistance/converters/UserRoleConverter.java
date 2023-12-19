package at.spengergasse.sj2324posproject.persistance.converters;

import at.spengergasse.sj2324posproject.domain.enums.UserRole;
import at.spengergasse.sj2324posproject.persistance.exception.DataQualityException;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Optional;

@Converter(autoApply = true)
public class UserRoleConverter implements AttributeConverter<UserRole, String> {

    @Override
    public String convertToDatabaseColumn(UserRole userRole) {
        return Optional.ofNullable(userRole)
                .map(ur -> switch (ur) {
                    case ADMIN -> "A";
                    case STANDARD -> "S";
                }).orElse(null);
    }

    @Override
    public UserRole convertToEntityAttribute(String dbValue) {
        return Optional.ofNullable(dbValue)
                .map(v -> {
                    return switch (v) {
                        case "A" -> UserRole.ADMIN;
                        case "S" -> UserRole.STANDARD;
                        default ->
                                throw DataQualityException.forInvalidValue(dbValue, UserRole.class);
                    };
                }).orElse(null);
    }
}
