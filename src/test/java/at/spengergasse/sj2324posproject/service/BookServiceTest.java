package at.spengergasse.sj2324posproject.service;

import at.spengergasse.sj2324posproject.domain.TestFixtures;
import at.spengergasse.sj2324posproject.persistence.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import java.util.Optional;

import static at.spengergasse.sj2324posproject.domain.TestFixtures.user;
import static at.spengergasse.sj2324posproject.domain.TestFixtures.user1;
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
    void ensureFetchBooksWithNoProvidedBookTitleCallsFindAll() {
        // given
        var bookTitle = Optional.<String>empty();
        var user = user();
        var book = TestFixtures.book(user);
        when(bookRepository.findAll()).thenReturn(List.of(book));

        // when
        var result = bookService.fetchBooks(bookTitle);

        // then
        assertThat(result).containsExactlyInAnyOrder(book);
        verify(bookRepository /*, times(1)*/).findAll();
        verifyNoMoreInteractions(bookRepository);
    }

    @Test
    void ensureFetchBooksWithProvidedBookTitleCallsFindAll() {
        // given
        var bookTitle = "Li";
        var user = user();
        var book = TestFixtures.book(user);
        when(bookRepository.findAllByBookTitleLikeIgnoreCase(bookTitle+"%")).thenReturn(List.of(book));

        // when
        var result = bookService.fetchBooks(Optional.of(bookTitle));

        // then
        assertThat(result).containsExactlyInAnyOrder(book);
        verify(bookRepository /*, times(1)*/).findAllByBookTitleLikeIgnoreCase(any());
        verifyNoMoreInteractions(bookRepository);
    }
}
