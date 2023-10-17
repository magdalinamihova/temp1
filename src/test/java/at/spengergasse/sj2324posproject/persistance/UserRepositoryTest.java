package at.spengergasse.sj2324posproject.persistance;

import at.spengergasse.sj2324posproject.domain.Gender;
import at.spengergasse.sj2324posproject.domain.Photo;
import at.spengergasse.sj2324posproject.domain.User;
import at.spengergasse.sj2324posproject.domain.UserRole;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;

import static org.assertj.core.api.InstanceOfAssertFactories.LOCAL_DATE;
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