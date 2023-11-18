package at.spengergasse.sj2324posproject.persistance;

import at.spengergasse.sj2324posproject.domain.entities.Book;
import at.spengergasse.sj2324posproject.domain.entities.Membership;
import at.spengergasse.sj2324posproject.domain.entities.MembershipRequest;
import at.spengergasse.sj2324posproject.domain.entities.ReadingGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MembershipRequestRepository extends JpaRepository<MembershipRequest, Long> {
    //Optional<MembershipRequest> findByRequestedReadingGroup(ReadingGroup requestedReadingGroup);
}