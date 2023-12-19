package at.spengergasse.sj2324posproject.persistance;
import at.spengergasse.sj2324posproject.domain.entities.Book;
import at.spengergasse.sj2324posproject.domain.entities.BorrowRecord;
import at.spengergasse.sj2324posproject.domain.entities.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static at.spengergasse.sj2324posproject.domain.TestFixtures.*;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class BorrowRecordRepositoryTest {
    @Autowired
    private BorrowRecordRepository borrowRecordRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setup() {
        User savedUser = userRepository.save(user());
        Book book = book(savedUser);
        Book savedBook = bookRepository.save(book);
        BorrowRecord borrowRecord = borrowRecord(savedUser,book);
        borrowRecordRepository.save(borrowRecord);

    }
    @Test
    void ensureSavingAndRereadingReviewWorks(){
        assertThat(borrowRecordRepository).isNotNull();
    }

}