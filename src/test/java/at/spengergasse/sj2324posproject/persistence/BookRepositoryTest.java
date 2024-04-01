package at.spengergasse.sj2324posproject.persistence;

import at.spengergasse.sj2324posproject.TestContainerConfiguration;
import at.spengergasse.sj2324posproject.domain.entities.Book;
import at.spengergasse.sj2324posproject.domain.entities.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.testcontainers.context.ImportTestcontainers;
import org.springframework.context.annotation.Import;
import at.spengergasse.sj2324posproject.domain.enums.Language;

import java.util.List;
import java.util.Optional;

import static at.spengergasse.sj2324posproject.domain.TestFixtures.*;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import(TestContainerConfiguration.class)
class BookRepositoryTest {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BookRepository bookRepository;

    private Book savedBook;

    @BeforeEach
    void setup() {
        clear(bookRepository, userRepository);
        User savedUser = userRepository.save(user());
        savedBook = book1(savedUser);
        bookRepository.save(savedBook);
    }

    @Test
    void ensureSavingAndRereadingBookWorks() {
        assertThat(bookRepository.findById(savedBook.getId())).isPresent();
    }

    @Test
    void ensureFindAllByBookTitleLikeIgnoreCaseWorks() {
        List<Book> books = bookRepository.findAllByBookTitleLikeIgnoreCase("Little%");
        assertThat(books).isNotEmpty();
        assertThat(books).allMatch(book -> book.getBookTitle().toLowerCase().contains("little"));
    }

    @Test
    void ensureFindByBookTitleWorks(){
        //TODO: find out why the repo has some titles other than Little Women if the clear method doesnt exist
        List<Book> books = bookRepository.findAll();
        for (Book book : books) {
            System.out.println("Book Title: " + book.getBookTitle());
        }

        Optional<Book> bookOptional = bookRepository.findByBookTitleIgnoreCase("Little Women");
        assertThat(bookOptional).isPresent();
        assertThat(bookOptional.get().getBookTitle()).isEqualTo("Little Women");
    }

    @Test
    void ensureFindAllByLanguageWorks() {
        List<Book> books = bookRepository.findAll();
        for (Book book : books) {
            System.out.println("Book language: " + book.getLanguage());
        }
        Language language = Language.ENGLISH;
        List<Book> result = bookRepository.findAllByLanguage(language);
        assertThat(result).isNotEmpty();
        assertThat(result).allMatch(book -> book.getLanguage() == language);
    }

    @Test
    void ensureFindByBookTitleAndPostedByWorks() {
        User user = userRepository.save(user());
        Book book = book2(user);
        bookRepository.save(book);

        Optional<Book> optionalBook = bookRepository.findByBookTitleAndPostedBy(book.getBookTitle(), user);
        assertThat(optionalBook).isPresent();
        assertThat(optionalBook.get()).isEqualTo(book);
    }

}

