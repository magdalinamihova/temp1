package at.spengergasse.sj2324posproject.domain.entities;

import at.spengergasse.sj2324posproject.domain.embeddables.Photo;
import at.spengergasse.sj2324posproject.domain.enums.BookStatus;
import at.spengergasse.sj2324posproject.domain.enums.Language;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.jpa.domain.AbstractPersistable;

import java.util.Date;
import java.util.Set;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder

@Entity
@Table(name = "books")
public class Book extends AbstractPersistable<Long> {
    @Column(length = 256, nullable = false)
    private @NotNull @NotEmpty String bookTitle;
    @Column(length = 256, nullable = false)
    private @NotNull @NotEmpty String author;
    @Column(nullable = false)
    private @NotNull @NotEmpty String bookDescription;
    @Column(columnDefinition = "CHAR(2) CHECK(language in ('EN','DE','FR','ES','IT','PT','NL','RU','PL','SV','NO','DA','FI','EL','CS','HU','RO','BG','SR','HR','BS','SK','SL'))")
    private Language language;
    @Column(length = 64, nullable = false)
    private @NotNull @NotEmpty String genre;
    @Embedded
    private Photo bookCover;
    @Column
    private boolean hardCover;
    @Column @Temporal(TemporalType.TIMESTAMP)
    private Date dueDate;
    @Column(columnDefinition = "CHAR(3) CHECK(book_status in ('AVL','BRW','MBR'))")
    private BookStatus bookStatus;

    //RELATIONSHIPS
    @OneToMany(mappedBy = "reviewedBook")
    private Set<Review> reviews;
    @ManyToOne
    private User postedBy;
}
