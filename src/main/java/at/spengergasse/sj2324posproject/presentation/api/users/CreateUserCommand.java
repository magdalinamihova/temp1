package at.spengergasse.sj2324posproject.presentation.api.users;

import at.spengergasse.sj2324posproject.domain.embeddables.Photo;
import at.spengergasse.sj2324posproject.domain.entities.Membership;
import at.spengergasse.sj2324posproject.domain.entities.ReadingGroup;
import at.spengergasse.sj2324posproject.domain.entities.Review;
import at.spengergasse.sj2324posproject.domain.enums.Gender;
import at.spengergasse.sj2324posproject.domain.enums.UserRole;
import at.spengergasse.sj2324posproject.domain.records.Address;
import at.spengergasse.sj2324posproject.domain.records.Email;
import at.spengergasse.sj2324posproject.domain.records.PhoneNumber;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Set;

public record CreateUserCommand(
        @NotBlank String username,
        @NotBlank String firstName,
        @NotBlank String lastName,
        @NotBlank String password,
        @NotNull Email email,
        @NotNull UserRole userRole,
        @Nullable PhoneNumber phoneNumber,
        @Nullable Address address,
        @Nullable Gender gender,
        @Nullable Photo profilePic,
        @Nullable Set<ReadingGroup> groupsOwned,
        @Nullable Set<Review> reviews, Set<Membership> memberships
    ) {
}
