package at.spengergasse.sj2324posproject.persistance;

import at.spengergasse.sj2324posproject.domain.entities.Book;
import at.spengergasse.sj2324posproject.domain.entities.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static at.spengergasse.sj2324posproject.persistance.domain.TestFixtures.book;
import static at.spengergasse.sj2324posproject.persistance.domain.TestFixtures.user;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class BookRepositoryTest {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BookRepository bookRepository;


    @BeforeEach
    void setup() {
        User savedUser = userRepository.save(user());
        Book book = book(savedUser);
        bookRepository.save(book);
    }

    @Test
    void ensureSavingAndRereadingBookWorks() {
        assertThat(bookRepository).isNotNull();
    }

    @Test
    void ensureFindByBookTitleWorks(){
        assertThat(bookRepository.findByBookTitle("Little Women")).isPresent();
    }

}
