package at.spengergasse.sj2324posproject.domain.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.jpa.domain.AbstractPersistable;

import java.util.Date;
import java.util.Set;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder

@Entity
@Table(name="readingGroups")
//TODO implement class
public class ReadingGroup extends AbstractPersistable<Long> {
    private @NotNull @NotEmpty String name;
    private @NotNull @NotEmpty String description;
    @ManyToMany(mappedBy = "memberOf")
    private Set<User> groupMembers;
    private int numOfMembers;
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;
    @ManyToOne
    private User createdBy;
    @OneToMany(mappedBy = "postedBy")
    private Set<Book> booksPosted;
    @OneToMany(mappedBy = "booksAvailable")
    private Set<Book> booksAvailable;
    @OneToMany(mappedBy = "booksBorrowed")
    private Set<Book> booksBorrowed;
    @OneToMany(mappedBy = "readingGroup")
    private Set<MembershipRequest> joinRequests;
}
