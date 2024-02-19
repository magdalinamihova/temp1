package at.spengergasse.sj2324posproject.presentation.api.users;

import at.spengergasse.sj2324posproject.domain.embeddables.Photo;
import at.spengergasse.sj2324posproject.domain.entities.Membership;
import at.spengergasse.sj2324posproject.domain.entities.ReadingGroup;
import at.spengergasse.sj2324posproject.domain.entities.Review;
import at.spengergasse.sj2324posproject.domain.entities.User;
import at.spengergasse.sj2324posproject.domain.enums.Gender;
import at.spengergasse.sj2324posproject.domain.enums.UserRole;
import at.spengergasse.sj2324posproject.domain.records.Address;
import at.spengergasse.sj2324posproject.domain.records.Email;
import at.spengergasse.sj2324posproject.domain.records.PhoneNumber;

import java.util.Set;

import static at.spengergasse.sj2324posproject.foundation.Ensurer.isNotNull;

public record UserDto(String key, String username, String firstName, String lastName, Email email, UserRole userRole,
                      PhoneNumber phoneNumber, Address address, Gender gender, Photo profilePic, Set<ReadingGroup> groupsOwned,
                      Set<Review> reviews, Set<Membership> memberships) {

    public UserDto(User user) {
        this(user.getKey(), user.getUsername(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getUserRole(),
                user.getPhoneNumber(), user.getAddress(), user.getGender(), user.getProfilePic(), user.getGroupsOwned(),
                user.getReviews(), user.getMemberships());
    }
}