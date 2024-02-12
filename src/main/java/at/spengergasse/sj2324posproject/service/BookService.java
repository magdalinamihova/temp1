package at.spengergasse.sj2324posproject.service;

import at.spengergasse.sj2324posproject.domain.embeddables.Photo;
import at.spengergasse.sj2324posproject.domain.entities.Book;
import at.spengergasse.sj2324posproject.domain.enums.BookStatus;
import at.spengergasse.sj2324posproject.domain.enums.Language;
import at.spengergasse.sj2324posproject.persistence.BookRepository;
import at.spengergasse.sj2324posproject.persistence.exception.BookAlreadyExistsException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@RequiredArgsConstructor
@Log4j2

@Service
@Transactional
public class BookService {
    private final BookRepository bookRepository;

    // used Person here - same as our User
    public Book register(String bookTitle, String author, String bookDescription, Language language, String genre,
                         Photo bookCover, boolean hardCover, Date dueDate, BookStatus bookStatus) {
        log.debug("Check if book {} exists", bookTitle);
        var bookExists = bookRepository.findByBookTitle((bookTitle));

        if (bookExists.isPresent()) {
            log.warn("Book {} exists, so throw an exception", bookTitle);
            throw new BookAlreadyExistsException(bookTitle);
        }

        // check password policy???

        // todo create and save
        log.info("Create book {}", bookTitle); // TODO

        // create, link and save token
        log.info("Created ???registration confirmation token??? {} for book {}", "abc", bookTitle);

        return null; // todo
    }
}
