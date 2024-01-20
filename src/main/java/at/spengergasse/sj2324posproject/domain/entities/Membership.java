package at.spengergasse.sj2324posproject.domain.entities;

import at.spengergasse.sj2324posproject.domain.entities.ReadingGroup;
import at.spengergasse.sj2324posproject.domain.entities.User;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.AbstractPersistable;

import java.util.Date;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)

@Entity
@Table(name="memberships")
public class Membership extends AbstractPersistable<Long> {
    @Column @Temporal(TemporalType.TIMESTAMP)
    private Date joinDate;

    //RELATIONSHIPS
    @ManyToOne
    private User member;
    @ManyToOne
    private ReadingGroup readingGroup;

    @Builder
    public Membership(Date joinDate, User member, ReadingGroup readingGroup) {
        this.joinDate = joinDate;
        this.member = member;
        this.readingGroup = readingGroup;
    }

    @PrePersist
    private void prePersist() {
        this.joinDate = new Date();
    }
}
