package at.spengergasse.sj2324posproject.persistence;

import at.spengergasse.sj2324posproject.domain.entities.Book;
import at.spengergasse.sj2324posproject.domain.entities.BookProjections;

import java.util.List;

public interface BookRepositoryCustom {
    List<Book> findByAuthorNamePart(String namePart);
    List<BookProjections.Overview> findOverviewByAuthorNamePart(String namePart);
}
