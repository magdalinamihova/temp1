package at.spengergasse.sj2324posproject.domain.entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.AbstractPersistable;

import java.util.Date;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)

@Entity
@Table(name="borrowRecords")
public class BorrowRecord extends AbstractPersistable<Long> {
    @Column @Temporal(TemporalType.TIMESTAMP)
    private Date borrowDate;
    @Column @Temporal(TemporalType.TIMESTAMP)
    private Date returnDate;

    //RELATIONSHIPS
    @ManyToOne
    private User borrower;
    @ManyToOne
    private Book borrowedBook;

    @Builder
    public BorrowRecord(Date borrowDate, Date returnDate, User borrower, Book borrowedBook) {
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
        this.borrower = borrower;
        this.borrowedBook = borrowedBook;
    }

    @PrePersist
    @PreUpdate
    private void prePersist() {
        this.borrowDate = new Date();
            returnDate = new Date(borrowDate.getTime() + (30 * 24 * 60 * 60 * 1000));
    }

}
