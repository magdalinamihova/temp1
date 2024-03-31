package at.spengergasse.sj2324posproject.presentation.api.users;

import at.spengergasse.sj2324posproject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor

@RestController
@RequestMapping("/api/users")
public class UserRestController {
    private final UserService service;

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
}
