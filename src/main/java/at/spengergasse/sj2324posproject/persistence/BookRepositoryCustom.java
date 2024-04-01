package at.spengergasse.sj2324posproject.persistence;

import at.spengergasse.sj2324posproject.domain.entities.Book;

import java.util.List;

public interface BookRepositoryCustom {
    List<Book> findByAuthorNamePart(String namePart);
}
