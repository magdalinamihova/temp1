package at.spengergasse.sj2324posproject.presentation.www;

import at.spengergasse.sj2324posproject.domain.entities.Book;
import at.spengergasse.sj2324posproject.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequiredArgsConstructor
@Slf4j

@Controller
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;

    @GetMapping
    private String getBooks(Model model){
        List<Book> books = bookService.getBooks();
        if(books.size()==1){
            model.addAttribute("book",books.getFirst());
            return "book/detail";
        }
        else{
            model.addAttribute("books",books);
            return "book/list";
        }
    }
    @GetMapping("/create")
    private String showCreateBook(Model model, @Valid CreateBookForm form, BindingResult bindingResult){
        model.addAttribute("form", new CreateBookForm());
        return  "book/create";
    }
//    @GetMapping("/create")
//    private String handleCreateBookFormSubmission(Model model, @Valid CreateBookForm form, BindingResult bindingResult){
//        model.addAttribute("form", new CreateBookForm());
//       return  "book/create";
//    }
}
