package at.spengergasse.sj2324posproject.domain;

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
    @NotNull @NotEmpty
    @Column(length = 64, nullable = false)
    private String name;
    @Column(length = 1024, nullable = false)
    private String description;
    @NotNull @PositiveOrZero
    private int width;
    @NotNull @PositiveOrZero
    private int height;
    @NotNull @NotEmpty
    private String filetype;
}
