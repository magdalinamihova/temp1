package at.spengergasse.sj2324posproject.persistance;

import at.spengergasse.sj2324posproject.domain.entities.ReadingGroup;
import at.spengergasse.sj2324posproject.domain.entities.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static at.spengergasse.sj2324posproject.domain.TestFixtures.*;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class ReadingGroupRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ReadingGroupRepository readingGroupRepository;

    @BeforeEach
    void setup() {
        User savedUser = userRepository.save(user());
        ReadingGroup readingGroup = readingGroup(savedUser);
        readingGroupRepository.save(readingGroup);
    }

    @Test
    void ensureSavingAndRereadingReadingGroupWorks() {
        assertThat(readingGroupRepository).isNotNull();
    }

    @Test
    void ensureFindByNameWorks() {
        assertThat(readingGroupRepository.findByName("Book Club 1")).isPresent();
    }
}
