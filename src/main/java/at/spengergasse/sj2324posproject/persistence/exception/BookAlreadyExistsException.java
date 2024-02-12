package at.spengergasse.sj2324posproject.persistence.exception;

public class BookAlreadyExistsException extends RuntimeException {
    private final String bookTitle;
    public BookAlreadyExistsException(String bookTitle) {
        this.bookTitle = bookTitle;
    }
}
