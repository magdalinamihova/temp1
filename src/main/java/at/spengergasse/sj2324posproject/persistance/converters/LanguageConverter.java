package at.spengergasse.sj2324posproject.persistance.converters;
import at.spengergasse.sj2324posproject.domain.enums.Language;
import at.spengergasse.sj2324posproject.persistance.exception.DataQualityException;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Optional;

@Converter(autoApply = true)
public class LanguageConverter implements AttributeConverter<Language, String> {

    @Override
    public String convertToDatabaseColumn(Language language) {
        return Optional.ofNullable(language)
                .map(l -> switch (l) {
                    case ENGLISH -> "EN";
                    case GERMAN -> "DE";
                    case FRENCH -> "FR";
                    case SPANISH -> "ES";
                    case ITALIAN -> "IT";
                    case PORTUGUESE -> "PT";
                    case DUTCH -> "NL";
                    case RUSSIAN -> "RU";
                    case POLISH -> "PL";
                    case SWEDISH -> "SV";
                    case NORWEGIAN -> "NO";
                    case DANISH -> "DA";
                    case FINNISH -> "FI";
                    case GREEK -> "EL";
                    case CZECH -> "CS";
                    case HUNGARIAN -> "HU";
                    case ROMANIAN -> "RO";
                    case BULGARIAN -> "BG";
                    case SERBIAN -> "SR";
                    case CROATIAN -> "HR";
                    case BOSNIAN -> "BS";
                    case SLOVAK -> "SK";
                    case SLOVENIAN -> "SL";
                })
                .orElse(null);
    }

    @Override
    public Language convertToEntityAttribute(String dbValue) {
        return Optional.ofNullable(dbValue)
                .map(v -> {
                    return switch (v) {
                        case "EN" -> Language.ENGLISH;
                        case "DE" -> Language.GERMAN;
                        case "FR" -> Language.FRENCH;
                        case "ES" -> Language.SPANISH;
                        case "IT" -> Language.ITALIAN;
                        case "PT" -> Language.PORTUGUESE;
                        case "NL" -> Language.DUTCH;
                        case "RU" -> Language.RUSSIAN;
                        case "PL" -> Language.POLISH;
                        case "SV" -> Language.SWEDISH;
                        case "NO" -> Language.NORWEGIAN;
                        case "DA" -> Language.DANISH;
                        case "FI" -> Language.FINNISH;
                        case "EL" -> Language.GREEK;
                        case "CS" -> Language.CZECH;
                        case "HU" -> Language.HUNGARIAN;
                        case "RO" -> Language.ROMANIAN;
                        case "BG" -> Language.BULGARIAN;
                        case "SR" -> Language.SERBIAN;
                        case "HR" -> Language.CROATIAN;
                        case "BS" -> Language.BOSNIAN;
                        case "SK" -> Language.SLOVAK;
                        case "SL" -> Language.SLOVENIAN;
                        default -> throw DataQualityException.forInvalidValue(dbValue, Language.class);
                    };
                })
                .orElse(null);
    }
}