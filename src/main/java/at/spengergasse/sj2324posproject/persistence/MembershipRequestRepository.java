package at.spengergasse.sj2324posproject.persistence;

import at.spengergasse.sj2324posproject.domain.entities.MembershipRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MembershipRequestRepository extends JpaRepository<MembershipRequest, Long> {
    //Optional<MembershipRequest> findByRequestedReadingGroup(ReadingGroup requestedReadingGroup);
}