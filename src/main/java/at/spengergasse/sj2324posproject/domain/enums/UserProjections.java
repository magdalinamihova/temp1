package at.spengergasse.sj2324posproject.domain.enums;

import com.querydsl.core.annotations.QueryProjection;

public class UserProjections {
    public record Overview(String name, String email) {
        @QueryProjection
        public Overview(String firstName, String lastName, String username) {
            this("%s, %s".formatted(lastName.toUpperCase(), firstName), username);
        }
    }
}
