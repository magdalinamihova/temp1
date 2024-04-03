package at.spengergasse.sj2324posproject.persistence;

import at.spengergasse.sj2324posproject.domain.entities.Book;
import at.spengergasse.sj2324posproject.domain.entities.User;
import at.spengergasse.sj2324posproject.domain.enums.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book,Long>,BookRepositoryCustom {
    List<Book> findAllByBookTitleLikeIgnoreCase(String bookTitle);
    Optional<Book> findByBookTitleIgnoreCase(String bookTitle);
    List<Book> findAllByLanguage(Language language);
    Optional<Book> findByBookTitleAndPostedBy(String bookTitle, User user);

    @Query("from Book b where b.author ilike ?1")
    List<Book> queryByAuthorNamePart(String namePart);

    //TODO: test
    void deleteByKey(String key);
}


