package at.spengergasse.sj2324posproject.presentation.api;

import at.spengergasse.sj2324posproject.presentation.api.dtos.BookDto;
import at.spengergasse.sj2324posproject.service.BookService;
import at.spengergasse.sj2324posproject.service.exceptions.BookNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static at.spengergasse.sj2324posproject.presentation.api.APIBase._SLASH;

@RequiredArgsConstructor
@Log4j2

@RestController
@RequestMapping(BookRestController.BASE_ROUTE)
public class BookRestController {

    protected static final String BASE_ROUTE = APIBase.API + "/books";
    protected static final String PATH_VAR_BOOKTITLE = "{bookTitle}";
    protected static final String PATH_GET_BOOK = _SLASH + PATH_VAR_BOOKTITLE;
    protected static final String GET_BOOK_ROUTE = BASE_ROUTE + PATH_GET_BOOK;

    private final BookService service;

    @GetMapping
    public HttpEntity<List<BookDto>> getBooks(@RequestParam Optional<String> bookTitleLike) {
        log.debug("searching books with bookTitle part: {}", bookTitleLike);
        List<BookDto> returnValue = service.fetchBooks(bookTitleLike)
                                            .stream()
                                            .map(BookDto::new)
                                            .toList();

        ResponseEntity<List<BookDto>> response = ResponseEntity.ok(returnValue);

        if(bookTitleLike.isEmpty() && returnValue.isEmpty()) response = ResponseEntity.noContent().build();
        if(bookTitleLike.isPresent() && returnValue.isEmpty()) response = ResponseEntity.notFound().build();

        return response;
    }

    @GetMapping(GET_BOOK_ROUTE)
    public HttpEntity<BookDto> getBook(@PathVariable String bookTitle) {
        log.debug("searching book with bookTitle: {}", bookTitle);
        return ResponseEntity.ok(new BookDto(service.getByBookTitle(bookTitle)));
    }
}
