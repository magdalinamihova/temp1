package at.spengergasse.sj2324posproject.presentation.api.dtos;

import at.spengergasse.sj2324posproject.domain.embeddables.Photo;
import at.spengergasse.sj2324posproject.domain.entities.Book;
import at.spengergasse.sj2324posproject.domain.entities.Review;
import at.spengergasse.sj2324posproject.domain.entities.User;
import at.spengergasse.sj2324posproject.domain.enums.BookStatus;
import at.spengergasse.sj2324posproject.domain.enums.Language;

import java.util.Date;
import java.util.Set;

public record BookDto(String bookTitle, String author, String bookDescription, Language language, String genre, Photo bookCover, boolean hardCover, Date dueDate, Set<Review> reviews, User postedBy, BookStatus bookStatus) {

    public BookDto(Book book) {
        this(book.getBookTitle(), book.getAuthor(), book.getBookDescription(), book.getLanguage(), book.getGenre(), book.getBookCover(), book.isHardCover(), book.getDueDate(), book.getReviews(), book.getPostedBy(), book.getBookStatus());
    }
}