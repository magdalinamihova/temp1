package at.spengergasse.sj2324posproject.persistence;

import at.spengergasse.sj2324posproject.TestContainerConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import static at.spengergasse.sj2324posproject.domain.TestFixtures.user;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import(TestContainerConfiguration.class)
class UserRepositoryTest {

    @Autowired
    private UserRepository repository;

    @BeforeEach
    void setup() {
        repository.save(user());
    }

    @Test
    void ensureSavingAndRereadingUserWorks(){
        assertThat(repository).isNotNull();
    }

    @Test
    void ensureFindingByNicknameWorks(){
        assertThat(repository.findByUsername("josie")).isPresent();
    }
}