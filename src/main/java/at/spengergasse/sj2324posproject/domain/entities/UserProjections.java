package at.spengergasse.sj2324posproject.domain.entities;

import com.querydsl.core.annotations.QueryProjection;

public class UserProjections {
    public record Overview(String name, String email) {
        @QueryProjection
        public Overview(String firstName, String lastName, String username) {
            this("%s, %s".formatted(lastName.toUpperCase(), firstName), username);
        }
    }

    public record NameOnly(String firstName, String lastName, String username) {

    }
}