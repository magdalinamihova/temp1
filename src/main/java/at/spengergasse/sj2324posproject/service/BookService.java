package at.spengergasse.sj2324posproject.service;

import at.spengergasse.sj2324posproject.domain.entities.Book;
import at.spengergasse.sj2324posproject.domain.enums.Language;
import at.spengergasse.sj2324posproject.persistence.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Log4j2

@Service
@Transactional(readOnly = true)
public class BookService {
    //private static final Logger logger = LoggerFactory.getLogger(BookService.class); dont do anymore
    //private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final BookRepository repository;

    public List<Book> fetchBooks(Optional<String> bookTitle, Optional<Language> language) {
        if (bookTitle.isPresent()) {
            return repository.findAllByBookTitle(bookTitle.get());
        } else if (language.isPresent()) {
            return repository.findAllByLanguage(language.get());
        } else {
            return repository.findAll();
        }
    }

    public Optional<Book> findByBookTitle(String bookTitle){return repository.findByBookTitle(bookTitle);}

}
