package at.spengergasse.sj2324posproject.persistence;

import at.spengergasse.sj2324posproject.domain.entities.User;
import at.spengergasse.sj2324posproject.domain.entities.UserProjections;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    List<User> findAllByUsername(String username);
    Optional<User> findByUsername(String username);

    boolean existsByUsername(String username);

    @Query("from User u where u.lastName ilike ?1 or u.firstName ilike ?1")
    List<User> queryByNamePart(String namePart);

    List<UserProjections.NameOnly> findAllByLastNameLikeIgnoreCase(String lastName);
}
