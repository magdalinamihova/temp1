package at.spengergasse.sj2324posproject.presentation.www;

import at.spengergasse.sj2324posproject.domain.embeddables.Photo;
import at.spengergasse.sj2324posproject.domain.entities.User;
import at.spengergasse.sj2324posproject.domain.enums.Language;
import io.micrometer.common.lang.Nullable;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter(AccessLevel.PROTECTED)

public final class CreateBookForm {
    private @NotNull @NotEmpty String bookTitle;
    private @NotNull @NotEmpty String author;
    private @NotNull @NotEmpty String bookDescription;
    private Language language;
    private @NotNull @NotEmpty String genre;
    private Photo bookCover;
    private boolean hardCover;
    private @Nullable Date dueDate;
    private @NotNull User postedBy;
}
