package at.spengergasse.sj2324posproject.domain.embeddables;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder

@Embeddable
public class Photo{
    @Column(length = 64, nullable = false)
    private  @NotNull @NotEmpty String name;
    @Column(length = 1024, nullable = false)
    private String description;
    private @NotNull @PositiveOrZero int width;
    private @NotNull @PositiveOrZero int height;
    private @NotNull @NotEmpty String filetype;
}
