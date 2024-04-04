package at.spengergasse.sj2324posproject.presentation.www.books;

import at.spengergasse.sj2324posproject.domain.embeddables.Photo;
import at.spengergasse.sj2324posproject.domain.enums.Language;
import io.micrometer.common.lang.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

@Slf4j
public record CreateBookForm (
        @NotBlank(message = "{books.error.bookTitle.notEmpty}") String bookTitle,
        @NotBlank(message = "{books.error.author.notEmpty}") String author,
        @NotBlank(message = "{books.error.bookDescription.notEmpty}") String bookDescription,
        @NotNull(message = "{books.error.language.notNull}") Language language,
        @NotBlank(message = "{books.error.genre.notEmpty}") String genre,
        Photo bookCover,
        boolean hardCover,
        @Nullable Date dueDate,
        @NotNull(message = "{books.error.postedById.notNull}") Long postedById
){
    public CreateBookForm{
        log.info("in record constructor of {}", this.getClass().getSimpleName());
    }

    public static CreateBookForm create(){
        return new CreateBookForm("","","", Language.ENGLISH,"",null,false,null,0L);
    }
}
