package at.spengergasse.sj2324posproject.persistance;

import at.spengergasse.sj2324posproject.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository repository;
    private User user;

    @BeforeEach
    void setup() {
        user = User.builder()
                .username("josie")
                .firstName("Josette")
                .lastName("Saltzman")
                .build();
    }

    @Test
    void ensureSavingAndRereadingUserWorks(){
        //when
        var saved = repository.save(user);
        //then
        assertThat(saved).isNotNull().isSameAs(user);
        assertThat(saved.getId()).isNotNull();
    }

    @Test
    void ensureFindingByNicknameWorks(){
        var saved = repository.save(user);
        //when
        var found = repository.findByUsername("josie");
        //then
        assertThat(found).isPresent();
    }
}