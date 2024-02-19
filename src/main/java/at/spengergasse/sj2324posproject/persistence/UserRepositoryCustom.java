package at.spengergasse.sj2324posproject.persistence;

import at.spengergasse.sj2324posproject.domain.entities.User;
import at.spengergasse.sj2324posproject.domain.enums.UserProjections;

import java.util.List;

public interface UserRepositoryCustom {

    List<User> findByNamePart(String namePart);

    List<UserProjections.Overview> findOverviewByNamePart(String namePart);
}
