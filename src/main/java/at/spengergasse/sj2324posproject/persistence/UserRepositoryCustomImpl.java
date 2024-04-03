package at.spengergasse.sj2324posproject.persistence;

import at.spengergasse.sj2324posproject.domain.entities.QUser;
import at.spengergasse.sj2324posproject.domain.entities.QUserProjections_Overview;
import at.spengergasse.sj2324posproject.domain.entities.User;
import at.spengergasse.sj2324posproject.domain.entities.UserProjections;
import jakarta.persistence.EntityManager;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Component;
// import org.springframework.jdbc.core.simple.JdbcClient;

import java.util.List;


@Component
public class UserRepositoryCustomImpl extends QuerydslRepositorySupport implements UserRepositoryCustom {
    private final JdbcClient jdbcClient;    // #todo actually JdbcClient -> spring-boot update!
    private final EntityManager em;

    //private final QUser = QUser.user;         // #todo Unger - nothing under target/generated-sources
    private final QUser user = QUser.user;

    public UserRepositoryCustomImpl(JdbcClient jdbcClient, EntityManager em) {
        super(User.class);
        this.jdbcClient = jdbcClient;
        this.em = em;
    }

    @Override
    public List<User> findByNamePart(String namePart) {
        // #todo - fix QUser
        return from(user)
                .where(
                        user.firstName.containsIgnoreCase(namePart).or(
                                user.lastName.containsIgnoreCase(namePart).or(
                                        user.username.containsIgnoreCase(namePart))))
                .fetch();
    }

    @Override
    public List<UserProjections.Overview> findOverviewByNamePart(String namePart) {
        // #todo - fix QUser
        return from(user)
                .where(
                        user.firstName.containsIgnoreCase(namePart).or(
                                user.lastName.containsIgnoreCase(namePart).or(
                                        user.username.containsIgnoreCase(namePart))))
                .select(new QUserProjections_Overview(user.firstName, user.lastName, user.username))
                .fetch();
    }
}