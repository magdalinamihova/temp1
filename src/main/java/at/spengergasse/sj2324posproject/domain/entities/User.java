package at.spengergasse.sj2324posproject.domain.entities;

import at.spengergasse.sj2324posproject.domain.embeddables.Photo;
import at.spengergasse.sj2324posproject.domain.enums.UserRole;
import at.spengergasse.sj2324posproject.domain.enums.Gender;
import at.spengergasse.sj2324posproject.domain.records.Address;
import at.spengergasse.sj2324posproject.domain.records.Email;
import at.spengergasse.sj2324posproject.domain.records.PhoneNumber;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.jpa.domain.AbstractPersistable;

import java.util.Set;

import static at.spengergasse.sj2324posproject.foundation.Ensurer.isNotNull;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)

@Entity
@Table(name="users")
public class User extends AbstractPersistable<Long> {
    @Column(length = 64)
    private @NotNull @NotEmpty @NotBlank String username;

    @Column(length = 32)
    private  @NotNull @NotEmpty @NotBlank String firstName;

    @Column(length = 64)
    private  @NotNull @NotEmpty @NotBlank String lastName;

    @Column(length = 256)
    private  @NotNull @NotEmpty @NotBlank String password;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "email", length = 64))
    private @NotNull Email email;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "phone_number"))
    private PhoneNumber phoneNumber;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "address"))
    private Address address;

    @Column(columnDefinition = "CHAR(1) CHECK(gender in ('f','m','o','u'))")
    private Gender gender;

    @Column(columnDefinition = "CHAR(1) CHECK(user_role in ('A','S'))")
    private @NotNull UserRole userRole;

    @Embedded
    private Photo profilePic;

    //#TODO nice ... still missing cascade options
    //RELATIONSHIPS
    @OneToMany(mappedBy = "createdBy")
    private Set<ReadingGroup> groupsOwned;

    @OneToMany(mappedBy = "reviewer")
    private Set<Review> reviews;

    @OneToMany(mappedBy = "member")
    private Set<Membership> memberships;

    @Builder
    public User(
            String username, String firstName, String lastName,
            String password, Email email, PhoneNumber phoneNumber,
            Address address, Gender gender, UserRole userRole, Photo profilePic,
            Set<ReadingGroup> groupsOwned, Set<Review> reviews, Set<Membership> memberships
    ) {
        this.username = isNotNull(username, "username");
        this.firstName = isNotNull(firstName, "firstName");
        this.lastName = isNotNull(lastName, "lastName");
        this.password = isNotNull(password, "password");
        this.email = isNotNull(email, "email");
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.gender = gender;
        this.userRole = isNotNull(userRole, "userRole");
        this.profilePic = profilePic;
        this.groupsOwned = groupsOwned;
        this.reviews = reviews;
        this.memberships = memberships;
    }

}
