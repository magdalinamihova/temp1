package at.spengergasse.sj2324posproject.domain.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;
import org.springframework.data.jpa.domain.AbstractPersistable;

import java.time.LocalDate;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder

@Entity
@Table(name="reviews")
public class Review extends AbstractPersistable<Long> {
    @ManyToOne
    private User user;

    @ManyToOne
    private Book book;

    @Column(nullable = false) @PositiveOrZero
    private double rating;

    @Column(nullable = false)
    private String comment;

    @Column(name = "review_date", nullable = false)
    private LocalDate reviewDate;

}
