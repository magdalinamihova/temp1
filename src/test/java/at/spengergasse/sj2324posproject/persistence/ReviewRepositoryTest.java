package at.spengergasse.sj2324posproject.persistence;

import at.spengergasse.sj2324posproject.TestContainerConfiguration;
import at.spengergasse.sj2324posproject.domain.entities.Book;
import at.spengergasse.sj2324posproject.domain.entities.Review;
import at.spengergasse.sj2324posproject.domain.entities.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import static at.spengergasse.sj2324posproject.domain.TestFixtures.*;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import(TestContainerConfiguration.class)
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
        Book book = book(savedUser);
        Book savedBook = bookRepository.save(book);
        Review review = review(savedUser,savedBook);
        reviewRepository.save(review);

    }
    @Test
    void ensureSavingAndRereadingReviewWorks(){
        assertThat(reviewRepository).isNotNull();
    }

    @Test
    void ensureFindByCommentWorks(){
        assertThat(reviewRepository.findByComment("comment")).isPresent();
    }

}