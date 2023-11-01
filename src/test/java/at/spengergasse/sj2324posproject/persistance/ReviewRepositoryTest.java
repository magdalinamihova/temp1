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

import static at.spengergasse.sj2324posproject.domain.TestFixtures.*;
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

    @BeforeEach
    void setup() {
        User savedUser = userRepository.save(user());
        Book book = book();
        book.setPostedBy(savedUser);
        Book savedBook = bookRepository.save(book);
        Review review = review();
        review.setUser(savedUser);
        review.setBook(savedBook);
        reviewRepository.save(review);

    }
    @Test
    void ensureSavingAndRereadingUserWorks(){
        assertThat(reviewRepository).isNotNull();
    }

    @Test
    void ensureFindByCommentWorks(){
        assertThat(reviewRepository.findByComment("comment")).isPresent();
    }

}