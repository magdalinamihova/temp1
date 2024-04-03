package at.spengergasse.sj2324posproject.presentation.api.users;

import at.spengergasse.sj2324posproject.domain.entities.User;
import at.spengergasse.sj2324posproject.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import static at.spengergasse.sj2324posproject.presentation.api.APIBase.API;

@RequiredArgsConstructor
@Slf4j

@RestController
@RequestMapping("/api/users")
public class UserRestController {
    private final UserService service;
    private final UserService userService;
    protected static final String BASE_ROUTE = API + "/users";

    @GetMapping
    public HttpEntity<List<UserDto>> getUsers(@RequestParam Optional<String> username) {
        List<UserDto> returnValue = service.fetchUsers(username)
                    .stream()
                    .map(UserDto::new)
                    .toList();

        return (returnValue.isEmpty())
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(returnValue);
    }

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody @Valid CreateUserCommand cmd) {
        User user = userService.register(cmd.username(), cmd.firstName(), cmd.lastName(), cmd.password(), cmd.email(),
                cmd.userRole(), cmd.phoneNumber(), cmd.address(), cmd.gender(), cmd.profilePic(),
                cmd.groupsOwned(), cmd.reviews(), cmd.memberships());
        URI location = URI.create("%s/%d".formatted(BASE_ROUTE, user.getId()));
        return ResponseEntity.created(location).body(new UserDto(user));
    }

}
