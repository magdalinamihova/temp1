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

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder

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
    private UserRole userRole;

    @Embedded
    private Photo profilePic;

    //RELATIONSHIPS
    @OneToMany(mappedBy = "createdBy")
    private Set<ReadingGroup> groupsOwned;
    @OneToMany(mappedBy = "reviewer")
    private Set<Review> reviews;
    @OneToMany(mappedBy = "member")
    private Set<Membership> memberships;


}
