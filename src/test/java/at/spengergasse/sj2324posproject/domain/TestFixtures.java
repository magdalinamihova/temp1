package at.spengergasse.sj2324posproject.domain;

import at.spengergasse.sj2324posproject.domain.embeddables.Photo;
import at.spengergasse.sj2324posproject.domain.entities.Book;
import at.spengergasse.sj2324posproject.domain.entities.Review;
import at.spengergasse.sj2324posproject.domain.entities.User;
import at.spengergasse.sj2324posproject.domain.enums.BookStatus;
import at.spengergasse.sj2324posproject.domain.enums.Gender;
import at.spengergasse.sj2324posproject.domain.enums.UserRole;
import at.spengergasse.sj2324posproject.persistance.BookRepository;
import at.spengergasse.sj2324posproject.persistance.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

//TODO implement testfixtures -- ill do it
@DataJpaTest
public class TestFixtures {

    public static Photo profilePic (){
        return Photo.builder()
                .name("profilpic")
                .description("description")
                .width(640)
                .height(480)
                .filetype("png")
                .build();
    }

    public static Photo bookCover (){
        return Photo.builder()
                .name("little women cover")
                .description("description")
                .width(800)
                .height(600)
                .filetype("png")
                .build();
    }

    public static User user (){
        return User.builder()
                .username("josie")
                .firstName("Josette")
                .lastName("Saltzman")
                .email("josie@developers.org")
                .password("jos123")
                .phoneNumber("test")
                .address("test")
                .gender(Gender.FEMALE)
                .userRole(UserRole.STANDARD)
                .profilePic(profilePic())
                .build();
    }

    public static Book book (){
        return Book.builder()
                .bookTitle("Little Women")
                .author("Louisa May Alcott")
                .bookDescription("Description")
                .genre("Period piece")
                .language("English")
                .bookCover(bookCover())
                .hardCover(true)
                .postedBy(user())
                .bookStatus(BookStatus.AVAILABLE)
                .rating(4.5f)
                .build();
    }

    public static Review review (){
        return Review.builder()
                .user(user())
                .book(book())
                .rating(1.5)
                .comment("comment")
                .reviewDate(LocalDate.now())
                .build();
    }

}
