package at.spengergasse.sj2324posproject.presentation.api.books;

import at.spengergasse.sj2324posproject.domain.entities.Book;
import at.spengergasse.sj2324posproject.domain.enums.Language;

import java.util.Date;

public record BookDto(String bookTitle, String author, String bookDescription, Language language, String genre) {

    public BookDto(Book book) {
        this(book.getBookTitle(), book.getAuthor(), book.getBookDescription(), book.getLanguage(), book.getGenre());
    }
}
