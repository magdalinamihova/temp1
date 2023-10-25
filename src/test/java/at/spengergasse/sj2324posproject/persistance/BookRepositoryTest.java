package at.spengergasse.sj2324posproject.persistance;

import at.spengergasse.sj2324posproject.domain.embeddables.Photo;
import at.spengergasse.sj2324posproject.domain.entities.Book;
import at.spengergasse.sj2324posproject.domain.entities.User;
import at.spengergasse.sj2324posproject.domain.enums.BookStatus;
import at.spengergasse.sj2324posproject.domain.enums.Gender;
import at.spengergasse.sj2324posproject.domain.enums.UserRole;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Date;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class BookRepositoryTest {
    @Autowired
    private UserRepository userRepository;
    private User user;
    private User savedUser;
    @Autowired
    private BookRepository repository;
    private Book book;
    private Book savedBook;


    @BeforeEach
    void setup() {
        user = User.builder()
                .username("josie")
                .firstName("Josette")
                .lastName("Saltzman")
                .email("josie@developers.org")
                .password("jos123")
                .phoneNumber("test")
                .address("test")
                .gender(Gender.FEMALE)
                .userRole(UserRole.STANDARD)
                .profilePic(Photo.builder()
                        .name("profpic")
                        .description("description")
                        .width(640)
                        .height(480)
                        .filetype("png")
                        .build())
                .build();
            savedUser = userRepository.save(user);
        book = Book.builder()
                .bookTitle("Little Women")
                .author("Louisa May Alcott")
                .bookDescription("Description")
                .language("English")
                .genre("Period piece")
                .bookCover(Photo.builder()
                        .name("little women cover")
                        .description("description")
                        .width(800)
                        .height(600)
                        .filetype("png")
                        .build())
                .hardCover(true)
                .postedBy(user)
                .bookStatus(BookStatus.AVAILABLE)
                .rating(4.5f)
                .build();
        savedBook = repository.save(book);
    }

    @Test
    void ensureSavingAndRereadingBookWorks() {
        assertThat(savedBook).isNotNull().isSameAs(book);
        assertThat(savedBook.getId()).isNotNull();
    }

    @Test
    void ensureFindByBookTitleWorks(){
        var found = repository.findByBookTitle("Little Women");
        //then
        assertThat(found).isPresent();
    }

}
