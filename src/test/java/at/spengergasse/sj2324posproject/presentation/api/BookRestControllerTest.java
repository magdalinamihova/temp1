package at.spengergasse.sj2324posproject.presentation.api;

import at.spengergasse.sj2324posproject.domain.TestFixtures;
import at.spengergasse.sj2324posproject.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static at.spengergasse.sj2324posproject.domain.TestFixtures.book;
import static at.spengergasse.sj2324posproject.domain.TestFixtures.user;
import static org.assertj.core.api.Assumptions.assumeThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BookRestController.class)
public class BookRestControllerTest {

    private @Autowired MockMvc mockMvc;
    private @MockBean BookService bookService;

    @BeforeEach
    void setup() {
        assumeThat(mockMvc).isNotNull();
        assumeThat(bookService).isNotNull();
    }

    @Test
    void ensureGetBooksRespondsWithStatusOkWhenDataIsAvailable() throws Exception {
        // given
        var user = user();
        var book = book(user);
        when(bookService.fetchBooks(any())).thenReturn(List.of(book));

        // expect
        var request = get(BookRestController.BASE_ROUTE).accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].bookTitle").value("Little Women"))
                .andExpect(jsonPath("$[0].author").value("Louisa May Alcott"))
                .andDo(print());
    }

    @Test
    void ensureGetBooksRespondsWithStatusNoContentWhenNoDataIsAvailable() throws Exception {
        // given
        when(bookService.fetchBooks(any())).thenReturn(Collections.emptyList());

        // expect
        var request = get(BookRestController.BASE_ROUTE).accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(request)
                .andExpect(status().isNoContent())
                .andDo(print());
    }

    @Test
    void ensureGetBooksRespondsWithStatusNotFoundWhenProvidedABookTitleAndNoDataIsAvailable() throws Exception {
        // given
        when(bookService.fetchBooks(any())).thenReturn(Collections.emptyList());

        // expect
        var request = get(BookRestController.BASE_ROUTE).accept(MediaType.APPLICATION_JSON)
                            .param("bookTitleLike", "The Great Gatsby");
        mockMvc.perform(request)
                .andExpect(status().isNotFound())
                .andDo(print());
    }

    @Test
    void ensureGetBookRespondsWithStatusOkWhenProvidedABookTitleAndDataIsAvailable() throws Exception {
        // given
        var user = user();
        var book = TestFixtures.book(user);
        when(bookService.findByBookTitle(any())).thenReturn(Optional.of(book));

        // expect
        var request = get(BookRestController.GET_BOOK_ROUTE, "Little Women").accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.bookTitle").value("Little Women"))
                .andExpect(jsonPath("$.author").value("Louisa May Alcott"))
                .andDo(print());
    }

    @Test
    void ensureGetBookRespondsWithStatusNotFoundWhenProvidedABookTitleAndNoDataIsAvailable() throws Exception {
        // given
        var user = user();
        var book = TestFixtures.book(user);
        when(bookService.findByBookTitle(any())).thenReturn(Optional.empty());

        // expect
        var request = get(BookRestController.GET_BOOK_ROUTE, "Little Women").accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(request)
                .andExpect(status().isNotFound())
                .andDo(print());
    }
}
