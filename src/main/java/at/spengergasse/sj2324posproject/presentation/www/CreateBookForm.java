package at.spengergasse.sj2324posproject.presentation.www;

import at.spengergasse.sj2324posproject.domain.embeddables.Photo;
import at.spengergasse.sj2324posproject.domain.entities.User;
import at.spengergasse.sj2324posproject.domain.enums.Language;
import io.micrometer.common.lang.Nullable;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import lombok.Getter;
import lombok.Setter;
import lombok.AccessLevel;

import java.util.Date;

@Slf4j
public record CreateBookForm (
        @NotNull @NotEmpty String bookTitle,
        @NotNull @NotEmpty String author,
        @NotNull @NotEmpty String bookDescription,
        Language language,  // Corrected type to Language enum
        @NotNull @NotEmpty String genre,
        Photo bookCover,
        boolean hardCover,
        @Nullable Date dueDate,
        @NotNull Long postedById
){
    public CreateBookForm{
        log.info("in record constructor of {}", this.getClass().getSimpleName());
    }

    public static CreateBookForm create(){
        return new CreateBookForm("","","", Language.ENGLISH,"",null,false,null,0L);
    }
}
