package at.spengergasse.sj2324posproject.persistence;
import at.spengergasse.sj2324posproject.TestContainerConfiguration;
import at.spengergasse.sj2324posproject.domain.entities.Book;
import at.spengergasse.sj2324posproject.domain.entities.BorrowRecord;
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
class BorrowRecordRepositoryTest {
    @Autowired
    private BorrowRecordRepository borrowRecordRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setup() {
        //#TODO you would not have needed the previous lines with cascade types set on the book to user relation
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