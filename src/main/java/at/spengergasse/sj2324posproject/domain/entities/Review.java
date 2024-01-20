package at.spengergasse.sj2324posproject.domain.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;
import org.springframework.data.jpa.domain.AbstractPersistable;

import java.time.LocalDate;
import java.util.Date;

import static at.spengergasse.sj2324posproject.foundation.Ensurer.isNotNull;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)

@Entity
@Table(name="reviews")
public class Review extends AbstractPersistable<Long> {
    @Column(nullable = false) @PositiveOrZero
    private @NotNull double rating;
    @Column(nullable = true)
    private String comment;
    @Column(name = "review_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date reviewDate;

    //RELATIONSHIPS
    @ManyToOne
    private User reviewer;
    @ManyToOne
    private Book reviewedBook;

    @Builder
    public Review(double rating, String comment, User reviewer, Book reviewedBook) {
        this.rating = isNotNull(rating,"rating");
        this.comment = comment;
        this.reviewer = reviewer;
        this.reviewedBook = reviewedBook;
    }

    @PrePersist
    private void prePersist() {
        this.reviewDate = new Date();
    }


}
