package at.spengergasse.sj2324posproject.persistance;

import at.spengergasse.sj2324posproject.domain.embeddables.Photo;
import at.spengergasse.sj2324posproject.domain.entities.Book;
import at.spengergasse.sj2324posproject.domain.entities.Review;
import at.spengergasse.sj2324posproject.domain.entities.User;
import at.spengergasse.sj2324posproject.domain.enums.BookStatus;
import at.spengergasse.sj2324posproject.domain.enums.Gender;
import at.spengergasse.sj2324posproject.domain.enums.UserRole;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class ReviewRepositoryTest {
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private UserRepository userRepository;

    private User user;
    private User savedUser;
    private Book book;
    private Book savedBook;
    private Review review;
    private Review savedReview;

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
                .postedBy(savedUser)
                .bookStatus(BookStatus.AVAILABLE)
                .rating(4.5f)
                .build();

        savedBook = bookRepository.save(book);

        review = review.builder()
                .user(savedUser)
                .book(savedBook)
                .rating(1.5)
                .comment("comment")
                .reviewDate(LocalDate.now())
                .build();
        savedReview = reviewRepository.save(review);
    }
    @Test
    void ensureSavingAndRereadingUserWorks(){
        assertThat(savedReview).isNotNull().isSameAs(review);
        assertThat(savedReview.getId()).isNotNull();
    }

    @Test
    void ensureFindByCommentWorks(){
        var found = reviewRepository.findByUser_Username("josie");
        assertThat(found).isPresent();
    }

}