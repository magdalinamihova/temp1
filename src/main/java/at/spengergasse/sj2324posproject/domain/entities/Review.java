package at.spengergasse.sj2324posproject.domain.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;
import org.springframework.data.jpa.domain.AbstractPersistable;

import java.time.LocalDate;
import java.util.Date;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder

@Entity
@Table(name="reviews")
public class Review extends AbstractPersistable<Long> {
    @Column(nullable = false) @PositiveOrZero
    private double rating;
    @Column(nullable = false)
    private String comment;
    @Column(name = "review_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date reviewDate;

    //RELATIONSHIPS
    @ManyToOne
    private User reviewer;
    @ManyToOne
    private Book reviewedBook;

    @PrePersist
    private void prePersist() {
        this.reviewDate = new Date();
    }


}
