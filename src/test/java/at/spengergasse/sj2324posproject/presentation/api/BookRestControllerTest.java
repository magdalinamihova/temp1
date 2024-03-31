package at.spengergasse.sj2324posproject.presentation.api;

import at.spengergasse.sj2324posproject.domain.enums.Language;
import at.spengergasse.sj2324posproject.service.BookService;
import at.spengergasse.sj2324posproject.persistence.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;

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

@WebMvcTest(BookRestController.class)
class BookRestControllerTest {

    private @Autowired MockMvc mockMvc;
    private @MockBean BookService bookService;
    private @MockBean BookRepository bookRepository;

    @BeforeEach
    void setup(){
        assumeThat(mockMvc).isNotNull();
        assumeThat(bookService).isNotNull();
    }

    @Test
    void ensureGetBooksRespondsWithStatusOkWhenDataIsAvailable() throws Exception{
        var request = get(BookRestController.BASE_ROUTE).accept(MediaType.APPLICATION_JSON);
        when(bookService.fetchBooks(Optional.empty(), Optional.empty())).thenReturn(List.of(book(user())));

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
                .param("bookTitle", title)
                .accept(MediaType.APPLICATION_JSON);

        when(bookService.fetchBooks(eq(Optional.of(title)), any())).thenReturn(Collections.emptyList());

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
        when(bookService.findByBookTitle(any())).thenReturn(Optional.of(book(user())));


        String title = "Little Women";
        var request = get(BookRestController.GET_BOOK_ROUTE,title)
                .accept(MediaType.APPLICATION_JSON);

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

        when(bookService.findByBookTitle(title)).thenReturn(Optional.empty());

        mockMvc.perform(request)
                .andExpect(status().isNotFound())
                .andDo(print());
    }

}