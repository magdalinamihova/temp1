package at.spengergasse.sj2324posproject.domain.entities;

import at.spengergasse.sj2324posproject.domain.embeddables.Photo;
import at.spengergasse.sj2324posproject.domain.enums.UserRole;
import at.spengergasse.sj2324posproject.domain.enums.Gender;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder

@Entity
@Table(name="users")
public class User extends AbstractPersistable<Long> {
    @Column(length = 32, nullable = false)
    private @NotNull @NotEmpty String username;
    @Column(length = 16, nullable = false)
    private  @NotNull @NotEmpty String firstName;
    @Column(length = 16, nullable = false)
    private  @NotNull @NotEmpty String lastName;
    @Column(length = 32, nullable = false)
    private  @NotNull @NotEmpty String email;
    @Column(length = 32, nullable = false)
    private  @NotNull @NotEmpty String password;
    private String phoneNumber;
    private String address;
    @Column(length = 1)
    private Gender gender;
    @Enumerated(EnumType.STRING)
    private UserRole role;
    @Embedded
    private Photo profilePic;
//    private Set<ReadingGroup> memberOf;
//    private Set<ReadingGroup> groupsOwned;
//    private Set<Review> reviews;
//    private Set<Membership> memberships;


}
