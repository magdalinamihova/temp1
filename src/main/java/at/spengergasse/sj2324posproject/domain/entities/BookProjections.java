package at.spengergasse.sj2324posproject.domain.entities;

import com.querydsl.core.annotations.QueryProjection;
//QUESTION: why are records allowed here, but weren't supported for Email, PhoneNumber and Address?
//java: java.lang.IllegalArgumentException: Illegal type: at.spengergasse.sj2324posproject.domain.records.Email

public class BookProjections {
    public record Overview(String book){
        @QueryProjection
        public Overview(String bookTitle, String author, String bookDescription){
            this("%s, %s, %s".formatted(bookTitle.toUpperCase(), author, bookDescription));
        }
    }
}