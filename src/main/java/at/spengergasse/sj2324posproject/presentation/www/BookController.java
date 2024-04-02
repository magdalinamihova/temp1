package at.spengergasse.sj2324posproject.presentation.www;

import at.spengergasse.sj2324posproject.domain.entities.Book;
import at.spengergasse.sj2324posproject.domain.entities.User;
import at.spengergasse.sj2324posproject.persistence.UserRepository;
import at.spengergasse.sj2324posproject.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Controller
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;
    private final UserRepository userRepository;

    @GetMapping
    public String getBooks(Model model) {
        List<Book> books = bookService.getBooks();
        if (books.size() == 1) {
            model.addAttribute("book", books.get(0));
            return "books/detail";
        } else {
            model.addAttribute("books", books);
            return "books/list";
        }
    }

    @GetMapping("/create")
    public String showCreateBook(Model model) {
        model.addAttribute("form", CreateBookForm.create());
        return "books/create";
    }

    @PostMapping("/create")
    public String handleCreateBookFormSubmission(Model model, @Valid @ModelAttribute(name="form") CreateBookForm form, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.warn("Validation failed: {}", bindingResult.getAllErrors());
            return "books/create";
        }
        User postedBy = userRepository.findById(form.postedById())
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id: " + form.postedById()));

        bookService.addBook(form.bookTitle(), form.author(), form.bookDescription(), form.language(), form.genre(), form.bookCover(), form.hardCover(), form.dueDate(), postedBy);
        log.info("worked");
        return "redirect:/books";
    }

}
