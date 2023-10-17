package at.spengergasse.sj2324posproject.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.jpa.domain.AbstractPersistable;

import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder

@Entity
@Table(name="users")
public class User extends AbstractPersistable<Long> {
    @NotNull @NotEmpty
    @Column(length = 20, nullable = false)
    private String username;
    @NotNull @NotEmpty
    @Column(length = 20, nullable = false)
    private String firstName;
    @NotNull @NotEmpty
    @Column(length = 20, nullable = false)
    private String lastName;
    @NotNull @NotEmpty
    @Column(length = 20, nullable = false)
    private String email;
    @NotNull @NotEmpty
    @Column(length = 20, nullable = false)
    private String password;
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
