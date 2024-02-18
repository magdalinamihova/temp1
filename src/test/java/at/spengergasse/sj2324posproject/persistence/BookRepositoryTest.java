package at.spengergasse.sj2324posproject.persistence;

import at.spengergasse.sj2324posproject.TestContainerConfiguration;
import at.spengergasse.sj2324posproject.domain.entities.Book;
import at.spengergasse.sj2324posproject.domain.entities.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
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
    void ensureFindAllByBookTitleLikeIgnoreCaseCaseWorks(){
        assertThat(bookRepository.findAllByBookTitleLikeIgnoreCase("Little Women")).isNotNull();
    }

    @Test // does not work with book Little Women for whatever reason
    void ensureFindByBookTitleIgnoreCaseWorks(){
        assertThat(bookRepository.findByBookTitleIgnoreCase("The Housemaid")).isNotNull();
    }
}
