package at.spengergasse.sj2324posproject.service;

import at.spengergasse.sj2324posproject.domain.embeddables.Photo;
import at.spengergasse.sj2324posproject.domain.entities.*;
import at.spengergasse.sj2324posproject.domain.enums.BookStatus;
import at.spengergasse.sj2324posproject.domain.enums.Gender;
import at.spengergasse.sj2324posproject.domain.enums.Language;
import at.spengergasse.sj2324posproject.domain.enums.UserRole;
import at.spengergasse.sj2324posproject.domain.records.Address;
import at.spengergasse.sj2324posproject.domain.records.Email;
import at.spengergasse.sj2324posproject.domain.records.PhoneNumber;
import at.spengergasse.sj2324posproject.persistence.BookRepository;
import at.spengergasse.sj2324posproject.persistence.UserRepository;
import at.spengergasse.sj2324posproject.persistence.exception.UserAlreadyExistsException;
import at.spengergasse.sj2324posproject.service.connectors.HttpBinConnector;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.ParameterizedType;
import java.util.*;

@RequiredArgsConstructor
@Log4j2

@Service
@Transactional
public class UserService {
    private final UserRepository userRepository;
    private final PasswordPolicy passwordPolicy;
    private final HttpBinConnector httpBin;

    public User register(String username, String firstName, String lastName, String password, Email email, UserRole userRole,
                         PhoneNumber phoneNumber, Address address, Gender gender, Photo profilePic, Set<ReadingGroup> groupsOwned,
                         Set<Review> reviews, Set<Membership> memberships) {
        log.debug("Check if user {} exists", username);
        var userExists = userRepository.findByUsername(username);

        if (userExists.isPresent()) {
            log.warn("User {} exists!", username);
            throw new UserAlreadyExistsException(username);
        }

        // check password policy
        log.debug("Ensure password complies to current policy!");
        passwordPolicy.complies(password);

        log.debug("Create user {}", username);
        User user = User.builder()
                .username(username)
                .firstName(firstName)
                .lastName(lastName)
                .password(password) //  encryption here
                .email(email)
                .key(httpBin.retrieveKey())
                .userRole(userRole)
                .phoneNumber(null)
                .address(null)
                .gender(null)
                .profilePic(null)
                .groupsOwned(null)
                .reviews(null)
                .memberships(null)
                .build();
        userRepository.save(user);
        log.info("Created user {} (id={}, key={})", username, user.getId(), user.getKey());

        log.debug("Create registration confirmation token for user {}", username);
        // TODO

        log.debug("Send confirmation notification for user {}", username);
        // TODO

        return user;
    }
}
