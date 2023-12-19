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
public class ReadingGroup extends AbstractPersistable<Long> {
    @Column(length = 64, nullable = false)
    private @NotNull @NotEmpty String name;
    @Column(length = 64, nullable = false)
    private @NotNull @NotEmpty String description;
    @Column @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;

    @ManyToOne
    private User createdBy;
    @OneToMany(mappedBy = "readingGroup")
    private Set<Membership> memberships;
    @OneToMany(mappedBy = "requestedReadingGroup")
    private Set<MembershipRequest> membershipRequests;

    @PrePersist
    private void prePersist() {
        this.creationDate = new Date();
    }

}
