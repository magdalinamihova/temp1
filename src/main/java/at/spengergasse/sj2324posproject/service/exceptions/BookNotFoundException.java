package at.spengergasse.sj2324posproject.service.exceptions;

import at.spengergasse.sj2324posproject.domain.entities.Book;

import java.util.Objects;

public class BookNotFoundException extends NotFoundException {

    public BookNotFoundException(String bookTitle, String message) {
        super(bookTitle, Book.class, message);
    }

    public static BookNotFoundException forBookTitle(String bookTitle) {
        Objects.requireNonNull(bookTitle);
        String message = "Book with bookTitle %s not found!".formatted(bookTitle);
        return new BookNotFoundException(bookTitle, message);
    }
}