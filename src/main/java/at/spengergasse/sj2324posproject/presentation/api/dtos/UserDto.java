package at.spengergasse.sj2324posproject.presentation.api.dtos;

import at.spengergasse.sj2324posproject.domain.entities.User;

public record UserDto(String username, String firstName, String lastName, String password) {
    public UserDto(User user) {
        this(user.getUsername(), user.getFirstName(), user.getLastName(), user.getPassword());
    }
}
