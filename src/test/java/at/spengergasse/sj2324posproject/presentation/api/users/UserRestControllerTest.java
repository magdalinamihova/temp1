package at.spengergasse.sj2324posproject.presentation.api.users;

import at.spengergasse.sj2324posproject.domain.TestFixtures;
import at.spengergasse.sj2324posproject.domain.entities.User;
import at.spengergasse.sj2324posproject.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(UserRestController.class)
public class UserRestControllerTest {

    private @Autowired MockMvc mockMvc;
    private @MockBean UserService userService;
    private @Autowired ObjectMapper mapper;

    @Test
    void ensureCreateUserReturnsCreatedWithLocationForValidData() throws Exception {
        // given
        Long userId = 4711L;
        User u = spy(TestFixtures.user());
        when(userService.register(any(), any(), any(), any(), any(), any(), any(), any(), any(), any(), any(), any(),any())).thenReturn(u);
        //when(userService.register(eq(u.getUsername()), eq(u.getFirstName()), eq(u.getLastName()), eq(u.getPassword()), eq(u.getEmail()), eq(u.getUserRole(),...))).thenReturn(u);
        when(u.getId()).thenReturn(userId);
        CreateUserCommand cmd = new CreateUserCommand(u.getUsername(), u.getFirstName(), u.getLastName(), u.getPassword(),
                                                      u.getPassword(), u.getEmail(), u.getUserRole(), u.getPhoneNumber(),
                                                      u.getAddress(), u.getGender(), u.getProfilePic(), u.getGroupsOwned(),
                                                      u.getReviews(), u.getMemberships());

        // expect
        var request = post(UserRestController.BASE_ROUTE).accept(MediaType.APPLICATION_JSON)
                                                         .contentType(MediaType.APPLICATION_JSON)
                                                         .content(mapper.writeValueAsString(cmd));

        mockMvc.perform(request)
                .andExpect(status().isCreated())
                .andExpect(header().string(HttpHeaders.LOCATION, "/api/users/4711"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.username").value(u.getUsername()))
                .andExpect(jsonPath("$.key").isNotEmpty())
                .andDo(print());
    }
}
