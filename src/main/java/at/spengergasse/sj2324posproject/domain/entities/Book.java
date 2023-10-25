package at.spengergasse.sj2324posproject.domain.entities;

import at.spengergasse.sj2324posproject.domain.embeddables.Photo;
import at.spengergasse.sj2324posproject.domain.enums.BookStatus;
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
    //TODO create an Enum for languages
    @Column(length = 32, nullable = false)
    private @NotNull @NotEmpty String language;
    @Column(length = 64, nullable = false)
    private @NotNull @NotEmpty String genre;
    @Embedded
    private Photo bookCover;
    private boolean hardCover;
    @ManyToOne
    private User postedBy;
    private Date dueDate;
    @Column(columnDefinition = "CHAR(3) CHECK(book_status in ('AVL','BRW','MBR'))")
    private BookStatus bookStatus;
    private float rating;

//    @OneToMany(mappedBy = "book")
//    private Set<Review> reviews;

}
