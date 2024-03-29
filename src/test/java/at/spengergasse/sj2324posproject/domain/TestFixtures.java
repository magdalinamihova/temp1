package at.spengergasse.sj2324posproject.domain;

import at.spengergasse.sj2324posproject.domain.embeddables.Photo;
import at.spengergasse.sj2324posproject.domain.entities.*;
import at.spengergasse.sj2324posproject.domain.enums.*;
import at.spengergasse.sj2324posproject.domain.records.Address;
import at.spengergasse.sj2324posproject.domain.records.Email;
import at.spengergasse.sj2324posproject.domain.records.PhoneNumber;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@DataJpaTest
public class TestFixtures {

    public static Photo profilePic (){

        Photo photo = Photo.builder()
                .name("littlewomencover.jpg")
                .description("Description")
                .width(800)
                .height(600)
                .filetype("jpg")
                .build();
        try {
            Path path = Paths.get("src/main/resources/images/littlewomencover.jpg");
            byte[] photoBytes = Files.readAllBytes(path);
            photo.uploadPhoto(photoBytes);
        } catch (IOException e) {
            throw new RuntimeException("Failed to read image file: " + e.getMessage(), e);
        }
        return photo;
    }

    public static Photo bookCover() {
        Photo photo = Photo.builder()
                .name("littlewomencover.jpg")
                .description("Description")
                .width(800)
                .height(600)
                .filetype("jpg")
                .build();
        try {
            Path path = Paths.get("src/main/resources/images/littlewomencover.jpg");
            byte[] photoBytes = Files.readAllBytes(path);
            photo.uploadPhoto(photoBytes);
        } catch (IOException e) {
            throw new RuntimeException("Failed to read image file: " + e.getMessage(), e);
        }

        return photo;
    }
    public static User user (){
        return User.builder()
                .username("josie")
                .firstName("Josette")
                .lastName("March")
                .email(Email.of("josie@gmail.com"))
                .password("jos123")
                .phoneNumber(PhoneNumber.of("+43123456789"))
                .address(Address.of("69 Liesingbachstraße", "Vienna", "1100", "Austria"))
                .gender(Gender.FEMALE)
                .userRole(UserRole.STANDARD)
                .profilePic(profilePic())
                .build();
    }

    public static User user1 (){
        return User.builder()
                .username("brooke")
                .firstName("brooke")
                .lastName("thompson")
                .email(Email.of("brooke@gmail.com"))
                .password("bro123")
                .phoneNumber(PhoneNumber.of("+43123456789"))
                .address(Address.of("69 Liesingbachstraße", "Vienna", "1100", "Austria"))
                .gender(Gender.FEMALE)
                .userRole(UserRole.STANDARD)
                .profilePic(profilePic())
                .build();
    }

    public static ReadingGroup readingGroup(User createdBy) {
        return ReadingGroup.builder()
                .name("Book Club 1")
                .description("description")
                .createdBy(createdBy)
                .build();
    }

    public static Membership membership(User member, ReadingGroup readingGroup) {
        return Membership.builder()
                .member(member)
                .readingGroup(readingGroup)
                .build();
    }

    public static Book book (User postedBy){
        return Book.builder()
                .bookTitle("Little Women")
                .author("Louisa May Alcott")
                .bookDescription("Description")
                .genre("Period piece")
                .language(Language.ENGLISH)
                .bookCover(bookCover())
                .hardCover(true)
                .postedBy(postedBy)
                .build();
    }

    public static Review review (User reviewer, Book reviewedBook){
        return Review.builder()
                .reviewer(reviewer)
                .reviewedBook(reviewedBook)
                .rating(1.5)
                .comment("comment")
                .build();
    }

    public static MembershipRequest membershipRequest (User requestingUser, ReadingGroup requestedRG){
        return MembershipRequest.builder()
                .requestingUser(requestingUser)
                .requestedReadingGroup(requestedRG)
                .status(RequestStatus.APPROVED)
                .build();
    }

    public static BorrowRecord borrowRecord (User borrower, Book borrowedBook){
        return BorrowRecord.builder()
                .borrower(borrower)
                .borrowedBook(borrowedBook)
                .build();
    }


}
