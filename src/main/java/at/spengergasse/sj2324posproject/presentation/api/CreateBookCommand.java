package at.spengergasse.sj2324posproject.presentation.api;

import at.spengergasse.sj2324posproject.domain.embeddables.Photo;
import at.spengergasse.sj2324posproject.domain.entities.User;
import at.spengergasse.sj2324posproject.domain.enums.BookStatus;
import at.spengergasse.sj2324posproject.domain.enums.Language;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.*;

import java.util.Date;
import java.util.Set;

public record CreateBookCommand (
    @NotNull @NotEmpty String bookTitle,
    @NotNull @NotEmpty String author,
    @NotNull @NotEmpty String bookDescription,
    Language language,
    @NotNull @NotEmpty String genre,
    @NotNull Photo bookCover,
    boolean hardCover,
    @Nullable Date dueDate,
    @NotNull User postedBy ) {
}
