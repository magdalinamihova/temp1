package at.spengergasse.sj2324posproject.presentation.www.books;

import at.spengergasse.sj2324posproject.domain.entities.Book;
import at.spengergasse.sj2324posproject.domain.entities.User;
import at.spengergasse.sj2324posproject.persistence.UserRepository;
import at.spengergasse.sj2324posproject.persistence.exception.BookAlreadyExistsException;
import at.spengergasse.sj2324posproject.presentation.www.RedirectForwardSupport;
import at.spengergasse.sj2324posproject.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Controller
@RequestMapping("/books")
public class BookController implements RedirectForwardSupport {
    private final BookService bookService;
    private final UserRepository userRepository;
    protected static final String BASE_ROUTE = "/books";

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
        boolean bookTitleExists = false;
        if (bindingResult.hasErrors()) {
            log.warn("Validation failed: {}", bindingResult.getAllErrors());
            model.addAttribute("form", form);
            model.addAttribute(BindingResult.MODEL_KEY_PREFIX + "form", bindingResult);
            return "books/create";
        }

        User postedBy = userRepository.findById(form.postedById())
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id: " + form.postedById()));

        try {
            bookService.addBook(form.bookTitle(), form.author(), form.bookDescription(), form.language(),
                    form.genre(), form.bookCover(), form.hardCover(), form.dueDate(), postedBy);
            log.info("Book successfully added");
        } catch (BookAlreadyExistsException e) {
            bindingResult.rejectValue("bookTitle", "book.title.exists", "The book already exists.");
            model.addAttribute("form", form);
            bookTitleExists = true;
            model.addAttribute("bookTitleExists", bookTitleExists);
            return "books/create";
        }
        return redirect(BASE_ROUTE);
    }

    @GetMapping("/edit/{key}")
    private String showEditBookForm(Model model, @PathVariable String key){
        return bookService.getBookByKey(key)
                .map(b -> new EditBookForm(b.getKey() ,b.getBookTitle(), b.getAuthor(),b.getBookDescription(),b.getLanguage(),b.getGenre(),null,b.isHardCover(), null, b.getPostedBy().getId()))
                .map(f -> model.addAttribute("form",f))
                .map(_ -> "books/edit" )
                .orElse(redirect(BASE_ROUTE));
    }

    @PostMapping("/edit/{key}")
    private String handleEditBookFormSubmission(Model model, @PathVariable String key, @Valid @ModelAttribute(name="form") EditBookForm form, BindingResult bindingResult) {
        boolean bookTitleExists = false;
        if(bindingResult.hasErrors()){
            log.warn("Validation failed: {}", bindingResult.getAllErrors());
            model.addAttribute("form", form);
            model.addAttribute(BindingResult.MODEL_KEY_PREFIX + "form", bindingResult);
            return "books/edit";
        }

        User postedBy = userRepository.findById(form.postedById())
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id: " + form.postedById()));

        try {
            bookService.updateBook(key, form.bookTitle(), form.author(), form.bookDescription(), form.language(),
                    form.genre(), form.bookCover(), form.hardCover(), form.dueDate(), postedBy);
            log.info("Book successfully updated");
        } catch (BookAlreadyExistsException e) {
            bindingResult.rejectValue("bookTitle", "book.title.exists", "The book already exists.");
            model.addAttribute("form", form);
            bookTitleExists = true;
            model.addAttribute("bookTitleExists", bookTitleExists);
            return "books/edit";
        }
        return redirect(BASE_ROUTE);
    }

    @GetMapping("/delete/{key}")
    private String deleteBook(@PathVariable String key) {
        bookService.deleteBook(key);
        log.info("Successfully deleted book with key: {}",key);
        return redirect(BASE_ROUTE);
    }

}
