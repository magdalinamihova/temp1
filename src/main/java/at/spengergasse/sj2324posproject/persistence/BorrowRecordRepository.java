package at.spengergasse.sj2324posproject.persistence;

import at.spengergasse.sj2324posproject.domain.entities.BorrowRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BorrowRecordRepository extends JpaRepository<BorrowRecord,Long> {

}
