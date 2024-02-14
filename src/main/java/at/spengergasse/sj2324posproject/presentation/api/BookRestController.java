package at.spengergasse.sj2324posproject.presentation.api;

import at.spengergasse.sj2324posproject.presentation.api.dtos.BookDto;
import at.spengergasse.sj2324posproject.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor

@RestController
@RequestMapping("/api/books")
public class BookRestController {

    private final BookService service;

    @GetMapping
    public HttpEntity<List<BookDto>> getBooks(@RequestParam Optional<String> bookTitleLike) {
        List<BookDto> returnValue = service.fetchBooks(bookTitleLike)
                    .stream()
                    .map(BookDto::new)
                    .toList();

        return (returnValue.isEmpty())
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(returnValue);
    }
}
