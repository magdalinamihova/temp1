package at.spengergasse.sj2324posproject.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import org.springframework.data.jpa.domain.AbstractPersistable;

import java.util.Date;
import java.util.Set;
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)

@Entity
@Table(name="users")
public class User extends AbstractPersistable<Long> {
    private String username;
    private String firstName;
    private String lastName;
   /* private String email;
    private String password;
    private Date dateOfBirth;
    private String phoneNumber;
    private String address;
    private Gender gender;
    private UserRole role;
    private Photo profilePic;
    private Set<ReadingGroup> memberOf;
    private Set<ReadingGroup> groupsOwned;
    private Set<Review> reviews;
    private Set<Membership> memberships;*/
    @Builder
    public User(String username,String firstName,String lastName) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = username;
    }


}
