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
    private BookRepository repository;
    private Book book;

    @BeforeEach
    void setup() {
        book = Book.builder()
                .bookTitle("Little Women")
                .author("Louisa May Alcott")
                .bookDescription("Description")
                .genre("Period piece")
                .language("English")
                .bookCover(Photo.builder()
                        .name("little women cover")
                        .description("description")
                        .width(800)
                        .height(600)
                        .filetype("png")
                        .build())
                .hardCover(true)
               .postedBy(User.builder()
                        .username("josie")
                        .firstName("Josette")
                        .lastName("Saltzman")
                        .email("josie@developers.org")
                        .password("jos123")
                        .phoneNumber("test")
                        .address("test")
                        .gender(Gender.FEMALE)
                        .role(UserRole.STANDARD)
                        .profilePic(Photo.builder()
                                .name("profpic")
                                .description("description")
                                .width(640)
                                .height(480)
                                .filetype("png")
                                .build())
                        .build())
                .bookStatus(BookStatus.AVAILABLE)
                .rating(4.5f)
                .build();
    }

    @Test
    void ensureSavingAndRereadingBookWorks() {
        // When
        var saved = repository.save(book);
        // Then
        assertThat(saved).isNotNull().isSameAs(book);
        assertThat(saved.getId()).isNotNull();
    }
}
