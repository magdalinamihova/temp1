package at.spengergasse.sj2324posproject.persistance;

import at.spengergasse.sj2324posproject.domain.enums.Gender;
import at.spengergasse.sj2324posproject.domain.embeddables.Photo;
import at.spengergasse.sj2324posproject.domain.entities.User;
import at.spengergasse.sj2324posproject.domain.enums.UserRole;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

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
                .userRole(UserRole.STANDARD)
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
        assertThat(saved.getUsername()).isNotNull().isEqualTo("josie");
    }

    @Test
    void ensureFindingByNicknameWorks(){
        //when
        var saved = repository.save(user);
        var found = repository.findByUsername("josie");
        //then
        //assertThat(saved.getUsername()).isNotNull().isEqualTo("josie");
        assertThat(found).isPresent();
    }
}