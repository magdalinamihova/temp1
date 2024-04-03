package at.spengergasse.sj2324posproject.persistence;

import at.spengergasse.sj2324posproject.domain.entities.ReadingGroup;
import at.spengergasse.sj2324posproject.domain.entities.ReadingGroupProjections;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReadingGroupRepository extends JpaRepository<ReadingGroup,Long> {
    Optional<ReadingGroup> findByName(String name);

    List<ReadingGroupProjections.NameOnly> findAllByNameLikeIgnoreCase(String name);
}
