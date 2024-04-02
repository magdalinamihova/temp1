package at.spengergasse.sj2324posproject.presentation.www;

import at.spengergasse.sj2324posproject.domain.entities.Book;
import at.spengergasse.sj2324posproject.service.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import java.util.List;

import static at.spengergasse.sj2324posproject.domain.TestFixtures.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BookController.class)
class BookControllerTest {
    private @Autowired MockMvc mockMvc;
   // private @Autowired ObjectMapper objectMapper;
    private @MockBean BookService bookService;

    @Test
    void  ensureGetBooksWithMoreThanOneResultShowsListView() throws Exception{
        Book book1 = book1(user());
        Book book2 = book2(user());
        List<Book> books = List.of(book1, book2);
        when(bookService.getBooks()).thenReturn(books);

        mockMvc.perform(get("/books"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("books",books))
                .andExpect(view().name("book/list"))
                .andDo(print());
    }
    @Test
    void  ensureGetBooksWithOnlyOneResultShowsDetailView() throws Exception{
        Book book1 = book1(user());
        List<Book> books = List.of(book1);
        when(bookService.getBooks()).thenReturn(books);

        mockMvc.perform(get("/books"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("book",book1))
                .andExpect(view().name("book/detail"))
                .andDo(print());
    }
}