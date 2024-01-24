package at.spengergasse.sj2324posproject.domain.entities;

import at.spengergasse.sj2324posproject.domain.enums.RequestStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.jpa.domain.AbstractPersistable;

import java.util.Date;
import java.util.Set;

import static at.spengergasse.sj2324posproject.foundation.Ensurer.isNotNull;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)

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

    @Builder
    public ReadingGroup(String name, String description, Date creationDate, User createdBy, Set<Membership> memberships, Set<MembershipRequest> membershipRequests) {
        this.name = isNotNull(name, "name");
        this.description = isNotNull(description, "description");
        this.creationDate = creationDate;
        this.createdBy = createdBy;
        this.memberships = memberships;
        this.membershipRequests = membershipRequests;
    }

    @PrePersist
    private void prePersist() {
        this.creationDate = new Date();
    }

    //#TODO why commented out?
//    public void addMembershipRequest(MembershipRequest membershipRequest) {
//        membershipRequest.setRequestedReadingGroup(this);
//        membershipRequest.setStatus(RequestStatus.PENDING);
//        this.membershipRequests.add(membershipRequest);
//    }
//
//    public void acceptMembershipRequest(MembershipRequest membershipRequest, User user) {
//        membershipRequest.setStatus(RequestStatus.APPROVED);
//        Membership membership = Membership.builder()
//                .joinDate(new Date())
//                .member(membershipRequest.getRequestingUser())
//                .readingGroup(this)
//                .build();
//        this.memberships.add(membership);
//        this.membershipRequests.remove(membershipRequest);
//    }
//
//    public void denyMembershipRequest(MembershipRequest membershipRequest) {
//        membershipRequest.setStatus(RequestStatus.REJECTED);
//        this.membershipRequests.remove(membershipRequest);
//    }
}
