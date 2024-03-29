package at.spengergasse.sj2324posproject.presentation.api.dtos;

import at.spengergasse.sj2324posproject.domain.entities.Book;
import at.spengergasse.sj2324posproject.domain.enums.Language;

public record BookDto(
        String name,
        String description,
        String genre,
        String author,
        Language language
) {
    public BookDto(Book book) {
        this(
                book.getBookTitle(),
                book.getBookDescription(),
                book.getGenre(),
                book.getAuthor(),
                book.getLanguage()
        );
    }
}
