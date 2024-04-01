//package at.spengergasse.sj2324posproject.service;
//
//import at.spengergasse.sj2324posproject.domain.entities.Book;
//import at.spengergasse.sj2324posproject.domain.enums.Language;
//import at.spengergasse.sj2324posproject.persistence.BookRepository;
//import at.spengergasse.sj2324posproject.persistence.exception.BookAlreadyExistsException;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.util.List;
//import java.util.Optional;
//
//import static at.spengergasse.sj2324posproject.domain.TestFixtures.*;
//import static java.util.Optional.empty;
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.assertj.core.api.Assertions.assertThatThrownBy;
//import static org.assertj.core.api.Assumptions.assumeThat;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//public class BookServiceTest {
//
//    private BookService bookService;
//
//    private @Mock BookRepository bookRepository;
//
//    @BeforeEach
//    void setup() {
//        assumeThat(bookRepository).isNotNull();
//        bookService = new BookService(bookRepository);
//    }
//
//    @Test
//    void ensureFetchBooksWithNoProvidedParametersCallsFindAll() {
//        var book1 = book1(user());
//        var book2 = book2(user1());
//        when(bookRepository.findAll()).thenReturn(List.of(book1, book2));
//
//        var result = bookService.fetchBooks(empty(), empty());
//
//        assertThat(result).containsExactlyInAnyOrder(book1, book2);
//        verify(bookRepository).findAll();
//        verifyNoMoreInteractions(bookRepository);
//    }
//
//    @Test
//    void ensureFetchBooksWithProvidedBookTitleCallsFindAllByBookTitle() {
//        var bookTitle = "Little Women";
//        var book = book1(user());
//        when(bookRepository.findAllByBookTitleLikeIgnoreCase("Little%20Women")).thenReturn(List.of(book));
//
//        var result = bookService.fetchBooks(Optional.of("Little%20Women"), empty());
//
//        assertThat(result).containsExactlyInAnyOrder(book);
//        verify(bookRepository).findAllByBookTitleLikeIgnoreCase("Little%20Women");
//        verifyNoMoreInteractions(bookRepository);
//    }
//
//    @Test
//    void ensureFetchBooksWithProvidedLanguageCallsFindAllByLanguage() {
//        Language language = Language.ENGLISH;
//        var book = book1(user());
//        when(bookRepository.findAllByLanguage(Language.valueOf(String.valueOf(language)))).thenReturn(List.of(book));
//
//        var result = bookService.fetchBooks(empty(), Optional.of(language));
//
//        assertThat(result).containsExactlyInAnyOrder(book);
//        verify(bookRepository).findAllByLanguage(eq(Language.valueOf(String.valueOf(language))));
//        verifyNoMoreInteractions(bookRepository);
//    }
//
//    @Test
//    void ensureFetchBooksWithNoProvidedParametersDoesNotCallFindAllByBookTitleOrFindAllByLanguage() {
//        var book = book1(user());
//        when(bookRepository.findAll()).thenReturn(List.of(book));
//
//        var result = bookService.fetchBooks(empty(), empty());
//
//        assertThat(result).containsExactlyInAnyOrder(book);
//        verify(bookRepository).findAll();
//        verifyNoMoreInteractions(bookRepository);
//    }
//
//    @Test
//    void ensureAddBookCallsRepositorySaveAndReturnsAddedBook() {
//        var bookTitle = "1984";
//        var author = "George Orwell";
//        var bookDescription = "A dystopian novel about the future.";
//        var language = Language.ENGLISH;
//        var genre = "Dystopian";
//        var bookCover = bookCover();
//        var hardCover = true;
//        var user = user();
//
//        var book = Book.builder()
//                .bookTitle(bookTitle)
//                .author(author)
//                .bookDescription(bookDescription)
//                .language(language)
//                .genre(genre)
//                .bookCover(bookCover)
//                .hardCover(hardCover)
//                .dueDate(null)
//                .postedBy(user)
//                .build();
//
//        when(bookRepository.findByBookTitleAndPostedBy(bookTitle, user)).thenReturn(Optional.empty());
//        when(bookRepository.save(any(Book.class))).thenReturn(book);
//
//        var result = bookService.addBook(bookTitle, author, bookDescription, language, genre, bookCover, hardCover, null, user);
//
//        assertThat(result).isEqualTo(book);
//        verify(bookRepository).findByBookTitleAndPostedBy(bookTitle, user);
//        verify(bookRepository).save(book);
//        verifyNoMoreInteractions(bookRepository);
//    }
//
//    @Test
//    void ensureAddBookThrowsExceptionWhenBookAlreadyExists() {
//        var bookTitle = "1984";
//        var author = "George Orwell";
//        var bookDescription = "A dystopian novel about the future.";
//        var language = Language.ENGLISH;
//        var genre = "Dystopian";
//        var bookCover = bookCover();
//        var hardCover = true;
//        var user = user();
//        var existingBook = book1(user);
//
//        when(bookRepository.findByBookTitleAndPostedBy(bookTitle, user)).thenReturn(Optional.of(existingBook));
//
//        assertThatThrownBy(() -> bookService.addBook(bookTitle, author, bookDescription, language, genre, bookCover, hardCover, null, user))
//                .isInstanceOf(BookAlreadyExistsException.class)
//                .hasMessage("Book with title '1984' already exists!");
//
//        verify(bookRepository).findByBookTitleAndPostedBy(bookTitle, user);
//        verifyNoMoreInteractions(bookRepository);
//    }
//}
