package at.spengergasse.sj2324posproject.presentation.api;

import at.spengergasse.sj2324posproject.domain.entities.Book;

import at.spengergasse.sj2324posproject.domain.enums.Language;
import at.spengergasse.sj2324posproject.presentation.api.dtos.BookDto;
import at.spengergasse.sj2324posproject.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static at.spengergasse.sj2324posproject.presentation.api.APIBase.API;
import static at.spengergasse.sj2324posproject.presentation.api.APIBase._SLASH;

@RequiredArgsConstructor
@Log4j2

@RestController
@RequestMapping(BookRestController.BASE_ROUTE)
public class BookRestController {
    protected static final String BASE_ROUTE = API + "/books";
    protected static final String PATH_VAR_BOOK_TITLE = "{bookTitle}";
    protected static final String PATH_GET_BOOK = _SLASH + PATH_VAR_BOOK_TITLE;
    protected static final String GET_BOOK_ROUTE = BASE_ROUTE + _SLASH + PATH_VAR_BOOK_TITLE;

    private final BookService service;

    @GetMapping
    public HttpEntity<List<BookDto>> getBooks(@RequestParam Optional<String> bookTitleLike, @RequestParam Optional<String> language) {
        log.debug("searching books with bookTitle part: {}", bookTitleLike);
        List<BookDto> returnValue = service.fetchBooks(
                        bookTitleLike,
                        language.map(Language::valueOf)
                )
                .stream()
                .map(BookDto::new)
                .toList();

        ResponseEntity response;
        if (returnValue.isEmpty() && (bookTitleLike.isPresent() || language.isPresent())) {response = ResponseEntity.notFound().build();}
        else if (returnValue.isEmpty()) {response = ResponseEntity.noContent().build();}
        else {response = ResponseEntity.ok(returnValue);}
        return response;
    }
    @GetMapping(PATH_GET_BOOK)
    public HttpEntity<BookDto> getBookByTitle(@PathVariable String bookTitle) {
        log.debug("Fetching book with title: {}", bookTitle);
        Optional<BookDto> book = service.findByBookTitle(bookTitle).map(BookDto::new);
        return book.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
