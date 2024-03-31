package at.spengergasse.sj2324posproject.service;

import at.spengergasse.sj2324posproject.domain.entities.Book;
import at.spengergasse.sj2324posproject.domain.enums.Language;
import at.spengergasse.sj2324posproject.persistence.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static at.spengergasse.sj2324posproject.domain.TestFixtures.*;
import static java.util.Optional.empty;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assumptions.assumeThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    private BookService bookService;

    private @Mock BookRepository bookRepository;

    @BeforeEach
    void setup() {
        assumeThat(bookRepository).isNotNull();
        bookService = new BookService(bookRepository);
    }

    @Test
    void ensureFetchBooksWithNoProvidedParametersCallsFindAll() {
        var book = book(user());
        var book1 = book(user1());
        when(bookRepository.findAll()).thenReturn(List.of(book, book1));

        var result = bookService.fetchBooks(empty(), empty());

        assertThat(result).containsExactlyInAnyOrder(book, book1);
        verify(bookRepository).findAll();
        verifyNoMoreInteractions(bookRepository);
    }

    @Test
    void ensureFetchBooksWithProvidedBookTitleCallsFindAllByBookTitle() {
        var bookTitle = "Little Women";
        var book = book(user());
        when(bookRepository.findAllByBookTitleLikeIgnoreCase("Little%20Women")).thenReturn(List.of(book));

        var result = bookService.fetchBooks(Optional.of("Little%20Women"), empty());

        assertThat(result).containsExactlyInAnyOrder(book);
        verify(bookRepository).findAllByBookTitleLikeIgnoreCase("Little%20Women");
        verifyNoMoreInteractions(bookRepository);
    }

    @Test
    void ensureFetchBooksWithProvidedLanguageCallsFindAllByLanguage() {
        Language language = Language.ENGLISH;
        var book = book(user());
        when(bookRepository.findAllByLanguage(Language.valueOf(String.valueOf(language)))).thenReturn(List.of(book));

        var result = bookService.fetchBooks(empty(), Optional.of(language));

        assertThat(result).containsExactlyInAnyOrder(book);
        verify(bookRepository).findAllByLanguage(eq(Language.valueOf(String.valueOf(language))));
        verifyNoMoreInteractions(bookRepository);
    }

    @Test
    void ensureFetchBooksWithNoProvidedParametersDoesNotCallFindAllByBookTitleOrFindAllByLanguage() {
        var book = book(user());
        when(bookRepository.findAll()).thenReturn(List.of(book));

        var result = bookService.fetchBooks(empty(), empty());

        assertThat(result).containsExactlyInAnyOrder(book);
        verify(bookRepository).findAll();
        verifyNoMoreInteractions(bookRepository);
    }
}
