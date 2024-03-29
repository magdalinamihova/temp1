package at.spengergasse.sj2324posproject.service;

import at.spengergasse.sj2324posproject.persistence.BookRepository;
import at.spengergasse.sj2324posproject.domain.entities.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static at.spengergasse.sj2324posproject.domain.TestFixtures.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    private BookService bookService;

    private @Mock BookRepository bookRepository;

    @BeforeEach
    void setup() {
        bookService = new BookService(bookRepository);
    }

    @Test
    void ensureFetchBooksWithNoProvidedBookTitleCallsFindAll() {
        // given
        var bookTitle = Optional.<String>empty();
        var book = book(user());
        var book1 = book(user1());
        when(bookRepository.findAll()).thenReturn(List.of(book, book1));

        // when
        var result = bookService.fetchBooks(bookTitle);

        // then
        assertThat(result).containsExactlyInAnyOrder(book, book1);
        verify(bookRepository).findAll();
        verifyNoMoreInteractions(bookRepository);
    }

    @Test
    void ensureFetchBooksWithProvidedBookTitleCallsFindAllByBookTitleContainingIgnoreCase() {
        // given
        var bookTitle = "Little";
        var book = book(user());
        when(bookRepository.findAllByBookTitleContainingIgnoreCase(bookTitle)).thenReturn(List.of(book));

        // when
        var result = bookService.fetchBooks(Optional.of(bookTitle));

        // then
        assertThat(result).containsExactlyInAnyOrder(book);
        verify(bookRepository).findAllByBookTitleContainingIgnoreCase(any());
        verifyNoMoreInteractions(bookRepository);
    }

    @Test
    void ensureFindByBookTitleCallsFindByBookTitle() {
        // given
        var bookTitle = "Little Women";
        var book = book(user());
        when(bookRepository.findByBookTitle(bookTitle)).thenReturn(Optional.of(book));

        // when
        var result = bookService.findByBookTitle(bookTitle);

        // then
        assertThat(result).isPresent().contains(book);
        verify(bookRepository).findByBookTitle(bookTitle);
        verifyNoMoreInteractions(bookRepository);
    }
}
