package at.spengergasse.sj2324posproject.persistence;

import at.spengergasse.sj2324posproject.TestContainerConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
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
    void ensureFindingUserByUsernameWorks(){
        assertThat(repository.findByUsername("josie")).isPresent();
    }

    @Test
    void ensureFindingByNamePartReturnsAResult(){
        assertThat(repository.findByNamePart("jo")).isNotNull().isNotEmpty();
    }

    @Test
    void ensureFindingOverviewByNamePartReturnsAResult(){
        assertThat(repository.findByNamePart("jo")).isNotNull().isNotEmpty();
    }

    @Test
    @Disabled
    void ensureReadAllNamesOnly(){
        assertThat(repository.findAllByLastNameLikeIgnoreCase("M")).isNotNull();
    }
}