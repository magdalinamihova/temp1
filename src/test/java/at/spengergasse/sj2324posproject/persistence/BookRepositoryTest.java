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

import static at.spengergasse.sj2324posproject.domain.TestFixtures.book;
import static at.spengergasse.sj2324posproject.domain.TestFixtures.user;
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
        User savedUser = userRepository.save(user());
        savedBook = book(savedUser);
        bookRepository.save(savedBook);
    }

    @Test
    void ensureSavingAndRereadingBookWorks() {
        assertThat(bookRepository.findById(savedBook.getId())).isPresent();
    }

    @Test
    void ensureFindByBookTitleWorks() {
        assertThat(bookRepository.findByBookTitle("Little Women")).isPresent();
    }

    @Test
    void ensureBookCoverUploadWorks() {
        Book fetchedBook = bookRepository.findById(savedBook.getId()).orElse(null);

        assertThat(fetchedBook).isNotNull();
        assertThat(fetchedBook.getBookCover()).isNotNull();

        assertThat(fetchedBook.getBookCover().getName()).isEqualTo("littlewomencover.jpg");
        assertThat(fetchedBook.getBookCover().getDescription()).isEqualTo("Description");
        assertThat(fetchedBook.getBookCover().getWidth()).isEqualTo(800);
        assertThat(fetchedBook.getBookCover().getHeight()).isEqualTo(600);
        assertThat(fetchedBook.getBookCover().getFiletype()).isEqualTo("jpg");
    }
}
