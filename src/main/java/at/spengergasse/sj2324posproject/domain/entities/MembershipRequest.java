package at.spengergasse.sj2324posproject.domain.embeddables;

import at.spengergasse.sj2324posproject.domain.entities.ReadingGroup;
import at.spengergasse.sj2324posproject.domain.entities.User;
import at.spengergasse.sj2324posproject.domain.enums.RequestStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder

//TODO implement
public class MembershipRequest {
    @ManyToOne
    private User user;
    @ManyToOne
    private ReadingGroup readingGroup;
    @Temporal(TemporalType.TIMESTAMP)
    private Date requestDate;
    @Enumerated(EnumType.STRING)
    private RequestStatus status;
}
