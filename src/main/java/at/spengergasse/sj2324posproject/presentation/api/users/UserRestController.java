package at.spengergasse.sj2324posproject.presentation.api.users;

import at.spengergasse.sj2324posproject.domain.entities.Membership;
import at.spengergasse.sj2324posproject.domain.entities.ReadingGroup;
import at.spengergasse.sj2324posproject.domain.entities.Review;
import at.spengergasse.sj2324posproject.domain.entities.User;
import at.spengergasse.sj2324posproject.service.UserService;
import jakarta.annotation.Nullable;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

import static at.spengergasse.sj2324posproject.presentation.api.APIBase.API;

@RequiredArgsConstructor
@Slf4j

@RestController
@RequestMapping(UserRestController.BASE_ROUTE)
public class UserRestController {
    private final UserService userService;
    protected static final String BASE_ROUTE = API + "/users";

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody @Valid CreateUserCommand cmd) {
        User user = userService.register(cmd.username(), cmd.firstName(), cmd.lastName(), cmd.password(), cmd.email(),
                                         cmd.userRole(), cmd.phoneNumber(), cmd.address(), cmd.gender(), cmd.profilePic(),
                                         cmd.groupsOwned(), cmd.reviews(), cmd.memberships());
        URI location = URI.create("%s/%d".formatted(BASE_ROUTE, user.getId()));
        return ResponseEntity.created(location).body(new UserDto(user));
    }
}
