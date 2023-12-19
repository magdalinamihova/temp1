package at.spengergasse.sj2324posproject.domain.entities;

import at.spengergasse.sj2324posproject.domain.entities.ReadingGroup;
import at.spengergasse.sj2324posproject.domain.entities.User;
import at.spengergasse.sj2324posproject.domain.enums.RequestStatus;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.AbstractPersistable;

import java.util.Date;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder

@Entity
@Table(name="membershipRequests")
public class MembershipRequest extends AbstractPersistable<Long> {
    @Column @Temporal(TemporalType.TIMESTAMP)
    private Date requestDate;
    @Column(columnDefinition = "CHAR(1) CHECK(user_role in ('A','P',R))")
    private RequestStatus status;

    //RELATIONSHIPS
    @ManyToOne
    private User requestingUser;
    @ManyToOne
    private ReadingGroup requestedReadingGroup;

    @PrePersist
    private void prePersist() {
        this.requestDate = new Date();
    }
}
