package at.spengergasse.sj2324posproject.service;

import at.spengergasse.sj2324posproject.domain.entities.Book;
import at.spengergasse.sj2324posproject.domain.enums.Language;
import at.spengergasse.sj2324posproject.persistence.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class BookService {

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
}
