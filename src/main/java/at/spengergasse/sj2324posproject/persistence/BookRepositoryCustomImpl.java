//package at.spengergasse.sj2324posproject.persistence;
//
//import at.spengergasse.sj2324posproject.domain.entities.Book;
//import jakarta.persistence.EntityManager;
//import lombok.RequiredArgsConstructor;
//import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
//import org.springframework.jdbc.core.simple.JdbcClient;
//import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//
//@Component
//public class BookRepositoryCustomImpl extends QuerydslRepositorySupport implements BookRepositoryCustom {
//
//    private final JdbcClient jdbcClient;
//    private final EntityManager em;
//
//    private final QBook book = QBook.book;
//
//    public BookRepositoryCustomImpl(JdbcClient jdbcClient, EntityManager em) {
//        super(Book.class);
//        this.jdbcClient = jdbcClient;
//        this.em = em;
//    }
//
//    @Override
//    public List<Book> findByAuthorNamePart(String namePart) {
//        return null;
//    }
//}
