package at.spengergasse.sj2324posproject.service;

import at.spengergasse.sj2324posproject.domain.embeddables.Photo;
import at.spengergasse.sj2324posproject.domain.entities.Book;
import at.spengergasse.sj2324posproject.domain.entities.User;
import at.spengergasse.sj2324posproject.domain.enums.BookStatus;
import at.spengergasse.sj2324posproject.domain.enums.Language;
import at.spengergasse.sj2324posproject.persistence.BookRepository;
import at.spengergasse.sj2324posproject.persistence.UserRepository;
import at.spengergasse.sj2324posproject.persistence.exception.UserAlreadyExistsException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Log4j2

@Service
@Transactional
public class UserService {
    private final UserRepository userRepository;

    public User register(String username, String firstName, String lastName, String password, String email) {
        log.debug("Check if user {} exists", username);
        var userExists = userRepository.findByUsername(username);

        if (userExists.isPresent()) {
            log.warn("User {} exists, so throw an exception", username);
            throw new UserAlreadyExistsException(username);
        }

        // todo create and save
        log.info("Create user {}", username); // TODO

        // create, link and save token
        log.info("Created registration confirmation token {} for user {}", "abc", username);

        return null; // todo
    }
}
