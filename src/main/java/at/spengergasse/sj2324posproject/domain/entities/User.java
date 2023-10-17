package at.spengergasse.sj2324posproject.domain.entities;

import at.spengergasse.sj2324posproject.domain.embeddables.Photo;
import at.spengergasse.sj2324posproject.domain.enums.UserRole;
import at.spengergasse.sj2324posproject.domain.enums.Gender;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.jpa.domain.AbstractPersistable;

import java.util.Set;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder

@Entity
@Table(name="users")
public class User extends AbstractPersistable<Long> {
    @Column(length = 64, nullable = false)
    private @NotNull @NotEmpty String username;
    @Column(length = 256, nullable = false)
    private  @NotNull @NotEmpty String firstName;
    @Column(length = 256, nullable = false)
    private  @NotNull @NotEmpty String lastName;
   @Column(length = 256, nullable = false)
    private  @NotNull @NotEmpty String password;
    @Column(length = 32, nullable = false)

    //TODO Implement email rich-type, phoneNumber class, Address class
    private  @NotNull @NotEmpty String email;
    @Column(length = 32)
    private String phoneNumber;
    @Column(length = 256)
    private String address;

    @Column(columnDefinition = "CHAR(1) CHECK(gender in ('f','m','o','u'))")
    private Gender gender;
    @Column(columnDefinition = "CHAR(1) CHECK(userRole in ('A','S'))")
    private UserRole role;
    @Embedded
    private Photo profilePic;
//    private Set<ReadingGroup> memberOf;
//    private Set<ReadingGroup> groupsOwned;
//    private Set<Review> reviews;
//    private Set<Membership> memberships;


}
