package at.spengergasse.sj2324posproject.presentation.www;

import at.spengergasse.sj2324posproject.domain.embeddables.Photo;
import at.spengergasse.sj2324posproject.domain.enums.Language;
import io.micrometer.common.lang.Nullable;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

@Slf4j
public record EditBookForm(
        @NotNull @NotEmpty String bookTitle,
        @NotNull @NotEmpty String author,
        @NotNull @NotEmpty String bookDescription,
        Language language,  // Corrected type to Language enum
        @NotNull @NotEmpty String genre,
        Photo bookCover,
        boolean hardCover,
        @Nullable Date dueDate,
        @NotNull Long postedById
) {
    public EditBookForm{
        log.info("In record constructor of {}", this.getClass().getSimpleName());
    }

    public static EditBookForm create(){ return new EditBookForm("","","",Language.ENGLISH, "",null,false,null,0L);}
}
