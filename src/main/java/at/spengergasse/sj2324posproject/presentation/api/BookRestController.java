package at.spengergasse.sj2324posproject.presentation.api;

import at.spengergasse.sj2324posproject.domain.entities.Book;
import at.spengergasse.sj2324posproject.persistence.BookRepository;
import at.spengergasse.sj2324posproject.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor

@RestController
@RequestMapping("/api/books")
public class BookRestController {

    private final BookRepository bookRepository;

    @GetMapping
    public List<Book> getBooks(){
        return bookRepository.findAll();
    }
}
