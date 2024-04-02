package at.spengergasse.sj2324posproject.presentation.api;

import at.spengergasse.sj2324posproject.domain.entities.Book;
import at.spengergasse.sj2324posproject.domain.entities.User;
import at.spengergasse.sj2324posproject.domain.enums.Language;
import at.spengergasse.sj2324posproject.persistence.UserRepository;
import at.spengergasse.sj2324posproject.presentation.api.dtos.BookDto;
import at.spengergasse.sj2324posproject.service.BookService;
import at.spengergasse.sj2324posproject.service.exceptions.BookNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClient;

import java.net.URI;
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
    private final UserRepository userRepository;

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
        return ResponseEntity.ok(new BookDto(service.getByBookTitle(bookTitle)));
    }

    @PostMapping
    public ResponseEntity<BookDto> createBook(@RequestBody @Valid CreateBookCommand cmd){
        Optional<User> userOptional = userRepository.findById(cmd.postedById());

        User user = userOptional.get();

        Book book = service.addBook( cmd.bookTitle(),
                    cmd.author(),
                    cmd.bookDescription(),
                    cmd.language(),
                    cmd.genre(),
                    cmd.bookCover(),
                    cmd.hardCover(),
                    cmd.dueDate(),
                    user);
            URI location = URI.create("%s/%d".formatted(BASE_ROUTE, book.getId()));
            return ResponseEntity.created(location).body(new BookDto(book));
        }
}
