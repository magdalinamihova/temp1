package at.spengergasse.sj2324posproject.service;

import at.spengergasse.sj2324posproject.domain.embeddables.Photo;
import at.spengergasse.sj2324posproject.domain.entities.Book;
import at.spengergasse.sj2324posproject.domain.entities.User;
import at.spengergasse.sj2324posproject.domain.enums.Language;
import at.spengergasse.sj2324posproject.foundation.LikeSupport;
import at.spengergasse.sj2324posproject.persistence.BookRepository;
import at.spengergasse.sj2324posproject.persistence.exception.BookAlreadyExistsException;
import at.spengergasse.sj2324posproject.service.connectors.HttpBinConnector;
import at.spengergasse.sj2324posproject.service.exceptions.BookNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestClient;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@Log4j2
@Service
@Transactional(readOnly = true)
public class BookService implements LikeSupport {

    private final BookRepository bookRepository;
    private final HttpBinConnector httpBin;

    public List<Book> fetchBooks(Optional<String> bookTitle, Optional<Language> language) {
        if (bookTitle.isPresent()) {
            return bookTitle
                    .map(this::fixLikeExpression)
                    .map(bookRepository::findAllByBookTitleLikeIgnoreCase)
                    .orElseGet(bookRepository::findAll);
        } else if (language.isPresent()) {
            return bookRepository.findAllByLanguage(language.get());
        } else {
            return bookRepository.findAll();
        }
    }

    public Optional<Book> findByBookTitle(String bookTitle) {
        return bookRepository.findByBookTitleIgnoreCase(bookTitle);
    }
    public Book getByBookTitle(String bookTitle) {
        return bookRepository.findByBookTitleIgnoreCase(bookTitle)
                .orElseThrow(() -> BookNotFoundException.forBookTitle(bookTitle));
    }
    @Transactional
    public Book addBook(String bookTitle, String author, String bookDescription, Language language,
                        String genre, Photo bookCover, boolean hardCover, Date dueDate, User postedBy) {
        log.debug("Check if book {} has been posted", bookTitle);
        var bookExists = bookRepository.findByBookTitleAndPostedBy(bookTitle,postedBy);

        if (bookExists.isPresent()) {
            log.warn("Book {} already posted!", bookTitle);
            throw new BookAlreadyExistsException(bookTitle);
        }

        log.debug("Post book {}",bookTitle);
        Book book = Book.builder()
                .bookTitle(bookTitle)
                .author(author)
                .bookDescription(bookDescription)
                .language(language)
                .genre(genre)
                .bookCover(bookCover)
                .hardCover(hardCover)
                .dueDate(dueDate)
                .postedBy(postedBy)
                .key(httpBin.retrieveKey())
                .build();
        bookRepository.save(book);
        log.info("Posted book {}", bookTitle);
        return book;
    }
}
