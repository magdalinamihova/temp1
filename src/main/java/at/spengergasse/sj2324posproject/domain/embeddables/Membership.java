package at.spengergasse.sj2324posproject.domain.embeddables;

import at.spengergasse.sj2324posproject.domain.entities.ReadingGroup;
import at.spengergasse.sj2324posproject.domain.entities.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
//TODO implement
@Embeddable
public class Membership {
    @ManyToOne
    private User user;
    @ManyToOne
    private ReadingGroup readingGroup;
    @Temporal(TemporalType.TIMESTAMP)
    private Date joinDate;
}
