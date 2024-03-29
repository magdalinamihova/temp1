package at.spengergasse.sj2324posproject.service;

import at.spengergasse.sj2324posproject.domain.entities.Book;
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
public class BookService {

    private final BookRepository repository;

    public List<Book> fetchBooks(Optional<String> bookTitle) {
        return bookTitle.map(repository::findAllByBookTitleContainingIgnoreCase)
                .orElseGet(repository::findAll);
    }

    public Optional<Book> findByBookTitle(String bookTitle) {
        return repository.findByBookTitle(bookTitle);
    }
}
