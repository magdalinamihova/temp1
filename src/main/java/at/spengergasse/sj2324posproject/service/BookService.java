package at.spengergasse.sj2324posproject.service;

import at.spengergasse.sj2324posproject.domain.entities.Book;
import at.spengergasse.sj2324posproject.foundation.LikeSupport;
import at.spengergasse.sj2324posproject.persistence.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Log4j2

@Service
@Transactional(readOnly = true)
public class BookService implements LikeSupport {

    // private static final Logger logger = LoggerFactory.getLogger(UserService.class); // don't do that anymore
    // private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final BookRepository bookRepository;

    public List<Book> fetchBooks(Optional<String> bookTitle) {
        return bookTitle
                .map(this::fixLikeExpression)
                .map(bookRepository::findAllByBookTitleLikeIgnoreCase)
                .orElseGet(bookRepository::findAll);
    }

    public Optional<Book> findByBookTitle(String bookTitle) { return bookRepository.findByBookTitleIgnoreCase(bookTitle);}
}
