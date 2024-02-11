package at.spengergasse.sj2324posproject.service;

import at.spengergasse.sj2324posproject.domain.entities.User;
import at.spengergasse.sj2324posproject.persistence.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor

@Service
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository repository;

    public List<User> fetchUsers(Optional<String> username) {
        return username.map(repository::findAllByUsername)
                .orElseGet(repository::findAll);
    }

    public Optional<User> findByUsername(String username) { return repository.findByUsername(username);}
}
