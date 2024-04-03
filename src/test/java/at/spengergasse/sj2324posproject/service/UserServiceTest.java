//package at.spengergasse.sj2324posproject.service;
//
//import at.spengergasse.sj2324posproject.persistence.UserRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import java.util.List;
//import java.util.Optional;
//
//import static at.spengergasse.sj2324posproject.domain.TestFixtures.user;
//import static at.spengergasse.sj2324posproject.domain.TestFixtures.user1;
//import static java.util.Optional.empty;
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.assertj.core.api.Assumptions.assumeThat;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//public class UserServiceTest {
//
//    private UserService userService;
//
//    private @Mock UserRepository userRepository;
//
//    @BeforeEach
//    void setup() {
//        assumeThat(userRepository).isNotNull();
//        userService = new UserService(userRepository);
//    }
//
//    @Test
//    void ensureFetchUsersWithNoProvidedUsernameCallsFindAll() {
//        var username = Optional.<String>empty();
//        var user = user();
//        var user1 = user1();
//        when(userRepository.findAll()).thenReturn(List.of(user, user1));
//
//        var result = userService.fetchUsers(username);
//
//        assertThat(result).containsExactlyInAnyOrder(user, user1);
//        verify(userRepository /*, times(1)*/).findAll();
//        verifyNoMoreInteractions(userRepository);
//    }
//
//    @Test
//    void ensureFetchUsersWithProvidedUsernameCallsFindAll() {
//        var username = "josie";
//        var user = user();
//        when(userRepository.findAllByUsername(username)).thenReturn(List.of(user));
//
//        var result = userService.fetchUsers(Optional.of(username));
//
//        assertThat(result).containsExactlyInAnyOrder(user);
//        verify(userRepository /*, times(1)*/).findAllByUsername(any());
//        verifyNoMoreInteractions(userRepository);
//    }
//}
