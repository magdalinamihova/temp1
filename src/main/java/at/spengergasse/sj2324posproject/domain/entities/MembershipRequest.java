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

@Entity
@Table(name="membership_requests")
public class MembershipRequest extends AbstractPersistable<Long> {
    @Column @Temporal(TemporalType.TIMESTAMP)
    private Date requestDate;
    @Column(columnDefinition = "CHAR(1) CHECK(request_status in ('A','P','R'))")
    private RequestStatus request_status;

    //RELATIONSHIPS
    @ManyToOne
    private User requestingUser;
    @ManyToOne
    private ReadingGroup requestedReadingGroup;

    @Builder
    public MembershipRequest(Date requestDate, RequestStatus request_status, User requestingUser, ReadingGroup requestedReadingGroup) {
        this.requestDate = requestDate;
        this.request_status = request_status;
        this.requestingUser = requestingUser;
        this.requestedReadingGroup = requestedReadingGroup;
    }

    @PrePersist
    private void prePersist() {
        this.requestDate = new Date();
    }



}
