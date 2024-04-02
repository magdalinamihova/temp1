package at.spengergasse.sj2324posproject.presentation.api;

import at.spengergasse.sj2324posproject.domain.TestFixtures;
import at.spengergasse.sj2324posproject.domain.entities.Book;
import at.spengergasse.sj2324posproject.domain.entities.User;
import at.spengergasse.sj2324posproject.domain.enums.Language;
import at.spengergasse.sj2324posproject.persistence.UserRepository;
import at.spengergasse.sj2324posproject.service.BookService;
import at.spengergasse.sj2324posproject.persistence.BookRepository;
import at.spengergasse.sj2324posproject.service.exceptions.BookNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static at.spengergasse.sj2324posproject.domain.TestFixtures.*;
import static org.assertj.core.api.Assumptions.assumeThat;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

@WebMvcTest(BookRestController.class)
class BookRestControllerTest {

    private @Autowired MockMvc mockMvc;
    private @Autowired ObjectMapper mapper;
    private @MockBean BookService bookService;
    private @MockBean BookRepository bookRepository;
    private @MockBean UserRepository userRepository;

    @BeforeEach
    void setup(){
        assumeThat(mockMvc).isNotNull();
        assumeThat(bookService).isNotNull();
        assumeThat(userRepository).isNotNull();
    }

    @Test
    void ensureGetBooksRespondsWithStatusOkWhenDataIsAvailable() throws Exception{
        var request = get(BookRestController.BASE_ROUTE).accept(MediaType.APPLICATION_JSON);
        when(bookService.fetchBooks(Optional.empty(), Optional.empty())).thenReturn(List.of(book1(user())));

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].bookTitle").value("Little Women"))
                .andDo(print());
    }
    @Test
    void ensureGetBooksRespondsWithStatusNoContentWhenNoDataIsAvailable() throws Exception{
        var request = get(BookRestController.BASE_ROUTE).accept(MediaType.APPLICATION_JSON);
        when(bookService.fetchBooks(Optional.empty(), Optional.empty())).thenReturn(Collections.emptyList());

        mockMvc.perform(request)
                .andExpect(status().isNoContent())
                .andDo(print());
    }

    @Test
    void ensureGetBooksRespondsWithStatusNotFoundWhenBookTitleIsProvidedAndNoDataIsAvailable() throws Exception {
        String title = "Little Women";
        var request = get(BookRestController.BASE_ROUTE)
                .param("bookTitleLike", title)
                .accept(MediaType.APPLICATION_JSON);

        when(bookService.fetchBooks(Optional.of(title), Optional.empty())).thenReturn(Collections.emptyList());

        mockMvc.perform(request)
                .andExpect(status().isNotFound())
                .andDo(print());
    }




    @Test
    void ensureGetBooksRespondsWithStatusNotFoundWhenLanguageIsProvidedAndNoDataIsAvailable() throws Exception {
        Language language = Language.ENGLISH;
        var request = get(BookRestController.BASE_ROUTE)
                .param("language", String.valueOf(language))
                .accept(MediaType.APPLICATION_JSON);

        when(bookService.fetchBooks(any(), eq(Optional.of(language)))).thenReturn(Collections.emptyList());

        mockMvc.perform(request)
                .andExpect(status().isNotFound())
                .andDo(print());
    }
    @Test
    void ensureGetBookByTitleRespondsWithStatusOkWhenDataIsAvailable() throws Exception {
        String title = "Little Women";
        var request = get(BookRestController.GET_BOOK_ROUTE.replace("{bookTitle}", title))
                .accept(MediaType.APPLICATION_JSON);

        when(bookService.getByBookTitle(title)).thenReturn(book1(user()));

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.bookTitle").value("Little Women"))
                .andDo(print());
    }

    @Test
    void ensureGetBookByTitleRespondsWithStatusNotFoundWhenNoDataIsAvailable() throws Exception {
        String title = "Little Women";
        var request = get(BookRestController.GET_BOOK_ROUTE.replace("{bookTitle}", title))
                .accept(MediaType.APPLICATION_JSON);

        when(bookService.getByBookTitle(title)).thenThrow(BookNotFoundException.forBookTitle(title));

        mockMvc.perform(request)
                .andExpect(status().isNotFound())
                .andDo(print());
    }

    @Test
    void ensureCreateBookReturnsCreatedWithLocationForValidData() throws Exception {
        Long bookId = 1234L;
        Book book = spy(TestFixtures.book1(user()));

        User mockUser = mock(User.class);
        Long userId = 1L; // Example user ID
        when(mockUser.getId()).thenReturn(userId); // Mocking a valid user ID
        when(userRepository.findById(any())).thenReturn(Optional.of(mockUser));

        when(bookService.addBook(any(), any(), any(), any(), any(), any(), anyBoolean(), any(), any())).thenReturn(book);
        when(book.getId()).thenReturn(bookId);

        CreateBookCommand cmd = new CreateBookCommand(book.getBookTitle(), book.getAuthor(), book.getBookDescription(),
                book.getLanguage(), book.getGenre(), book.getBookCover(),
                book.isHardCover(), book.getDueDate(), userId);

        var request = post(BookRestController.BASE_ROUTE).accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(cmd));

        mockMvc.perform(request)
                .andExpect(status().isCreated())
                .andExpect(header().string(HttpHeaders.LOCATION, "/api/books/1234"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.bookTitle").value(book.getBookTitle()))
                .andDo(print());
    }


}