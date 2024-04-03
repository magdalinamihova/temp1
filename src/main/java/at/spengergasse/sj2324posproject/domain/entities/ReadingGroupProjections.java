package at.spengergasse.sj2324posproject.domain.entities;

import com.querydsl.core.annotations.QueryProjection;

public class ReadingGroupProjections {
    public record NameOnly(String name) {
        @QueryProjection
        public NameOnly {
        }
    }
}
