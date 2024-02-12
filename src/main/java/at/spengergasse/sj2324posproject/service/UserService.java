package at.spengergasse.sj2324posproject.service;

import at.spengergasse.sj2324posproject.domain.entities.User;
import at.spengergasse.sj2324posproject.persistence.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Log4j2

@Service
@Transactional(readOnly = true)
public class UserService {

    // private static final Logger logger = LoggerFactory.getLogger(UserService.class); // don't do that anymore
    // private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final UserRepository repository;

    public List<User> fetchUsers(Optional<String> username) {
        return username.map(repository::findAllByUsername)
                .orElseGet(repository::findAll);
    }

    public Optional<User> findByUsername(String username) { return repository.findByUsername(username);}
}
